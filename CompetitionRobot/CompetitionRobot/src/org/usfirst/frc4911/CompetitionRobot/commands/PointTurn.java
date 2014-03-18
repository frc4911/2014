package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4911.CompetitionRobot.RobotConstants;
import org.usfirst.frc4911.CompetitionRobot.subsystems.DriveSystem;
import org.usfirst.frc4911.CompetitionRobot.subsystems.Sensors;
import org.usfirst.frc4911.CompetitionRobot.Robot;

public class  PointTurn extends Command {
    DriveSystem driveSystem = Robot.driveSystem;
    Sensors sensors = Robot.sensors;
    
    private double goalAngle;
    private double power;
    
    private double lastAngleError;
    private double anglePIDintegral;
    private double toleranceTimeStart;
    private double stallTimeStart;  
    private double stallTimeLimit;
    
    private double ratePIDintegral;
    private double lastAngle;
    private double lastRateError;
    private double lastTime;
    private double lastGoalRateDPS;
    private double sum;
    private int count;
    private double timeDelta;
    private double delayTime;
    private double lastFPGATime;
    
    public PointTurn(double goal) {
        requires(Robot.driveSystem);
        requires(Robot.sensors);
        goalAngle = goal;
    }

    protected void initialize() {
        delayTime = 0.080;
        lastFPGATime = Timer.getFPGATimestamp() - delayTime;
        driveSystem.stop();
        sensors.reset();
        power = 1.0;
        positionPIDInit();
        turnRatePIDInit();
        System.out.println("INITIALIZED");
    }

    protected void execute() {
        timeDelta = Timer.getFPGATimestamp() - lastFPGATime;
        if (timeDelta > delayTime) {
            power = positionPID();
            lastFPGATime += delayTime;
        }
        driveSystem.drive(power, -power);  
    }

    protected boolean isFinished() {
        return (power == 0 || (Timer.getFPGATimestamp() - stallTimeStart >= stallTimeLimit));
    }

    protected void end() {
        driveSystem.stop();
        System.out.println("STOPPED");
    }

    protected void interrupted() {
    }
    
    //************************************************************************//
    public void positionPIDInit(){
        toleranceTimeStart = Timer.getFPGATimestamp();
        stallTimeStart = Timer.getFPGATimestamp();
        stallTimeLimit = goalAngle / 25.0;
        lastAngleError = 0.0;
        anglePIDintegral = 0.0;
    }
    
    public double positionPID(){
        boolean done = false;
        
        double currentAngle = sensors.getAngle();
        double currentError = goalAngle - currentAngle;
        
        if(Math.abs(currentError) > RobotConstants.TOLERANCE){
            toleranceTimeStart = Timer.getFPGATimestamp();
        }
        else if(Timer.getFPGATimestamp() - toleranceTimeStart > RobotConstants.TOLERANCE_TIME_LIMIT){
            done = true;
        }
        
        double iterationPower = 0.0;
        
        if(!done){
            double proportion = currentError;
            anglePIDintegral += currentError;
            double derivative = currentError - lastAngleError;
            lastAngleError = currentError;

            double correction = proportion * RobotConstants.KP_ANGLE + anglePIDintegral * RobotConstants.KI_ANGLE + derivative * RobotConstants.KD_ANGLE;
            double rate = (correction >= 0) ? Math.min(correction, 200.0) : Math.max(-200.0, correction);
            
            if(rate > 0){
                rate = Math.min(rate, 180.0);//Maximum Speed
                rate = Math.max(rate, 9.0);//Minimum Speed
            } else {
                rate = Math.max(rate, -180.0);
                rate = Math.min(rate, -9.0);
            }

            System.out.println("goalA\t" + goalAngle +  "\tcurrA\t" + currentAngle +  "\trate\t" + rate); 
            System.out.println("    \t\t\tp\t" + (RobotConstants.KP_ANGLE * proportion) +  "\ti\t" + (RobotConstants.KI_ANGLE * anglePIDintegral) +  "\td\t" + (RobotConstants.KD_ANGLE * derivative));
            
            iterationPower = turnRatePID(rate);
        }
        
        
        return iterationPower;
    }
    
    public void turnRatePIDInit(){
        ratePIDintegral = 0.0;
        lastAngle = 0.0;
        lastRateError = 0.0;
        lastTime = 0.0;
        lastGoalRateDPS = 0.0;
        
        sum = 0.0;
        count = 0; 
    }
    
    public double turnRatePID(double goalRateDPS){
        if(lastGoalRateDPS < 0 && goalRateDPS > 0 || lastGoalRateDPS > 0 && goalRateDPS < 0) {
            ratePIDintegral = 0;
        }
        
        double currTime = Timer.getFPGATimestamp();
        double currTimeSliceSecs = currTime - lastTime;
        double currAngle = sensors.getAngle();
        double currentDPS = (currAngle - lastAngle) / currTimeSliceSecs;        
        
        //double currentDPS = myGyro.getRate();
        
        sum += currentDPS; 
        count++; 
          
        
        lastTime = currTime;
        lastAngle = currAngle;

        double error = goalRateDPS - currentDPS;
     
        ratePIDintegral += error;
        double derivative = error - lastRateError;
        lastRateError = error;
        
        double p = error * RobotConstants.KP_TURNRATE;
        double i = ratePIDintegral * RobotConstants.KI_TURNRATE;
        double d = derivative * RobotConstants.KD_TURNRATE;
        
        double correction = p + i + d;        
        
        if(goalRateDPS > 0 && correction < 0){
            correction = RobotConstants.MINIMUM_TURN_POWER;
            ratePIDintegral = 0;
        }
        else if (goalRateDPS < 0 && correction > 0){
            correction = -RobotConstants.MINIMUM_TURN_POWER;
            ratePIDintegral = 0;
        }
        System.out.println("GoalDPS:\t" + goalRateDPS + "\tActualDPS:\t" + currentDPS +  "\tAverageDPS:\t" + (sum / count) + "\tPower:\t" + correction); 
        System.out.println("\t\t\tP:\t" + p + "\tI:\t" + i + "\tD:\t" + d); 
        
        lastGoalRateDPS = goalRateDPS;
        return correction;
    }
}
