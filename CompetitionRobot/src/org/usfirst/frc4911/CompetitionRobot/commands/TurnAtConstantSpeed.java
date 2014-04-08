package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4911.CompetitionRobot.subsystems.DriveSystem;
import org.usfirst.frc4911.CompetitionRobot.subsystems.Sensors;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.RobotConstants;

public class TurnAtConstantSpeed extends Command {
    DriveSystem driveSystem = Robot.driveSystem;
    Sensors sensors = Robot.sensors;
    
    private double goalSpeed;
    private double power;
    
    private double sum;
    private int count;
    
    private double ratePIDintegral;
    private double lastAngle;
    private double lastRateError;
    private double lastTime;
    private double lastGoalRateDPS;
    
    public TurnAtConstantSpeed(double goal) {
        requires(Robot.driveSystem);
        requires(Robot.sensors);
        goalSpeed = goal;
    }

    protected void initialize(){
        sensors.reset();        
        turnRatePIDInit();
    }
    
    protected void execute(){
        power = turnRatePID(goalSpeed);
        driveSystem.drive(power , -power);
    }
    
    protected boolean isFinished(){
        return false;
    }
    
    protected void end(){
        new OperatorDrive().start();
    }
    
    protected void interrupted(){    
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
        //System.out.println("GoalDPS:\t" + goalRateDPS + "\tActualDPS:\t" + currentDPS +  "\tAverageDPS:\t" + (sum / count) + "\tPower:\t" + correction); 
        //System.out.println("\t\t\tP:\t" + p + "\tI:\t" + i + "\tD:\t" + d); 
        
        lastGoalRateDPS = goalRateDPS;
        return correction;
    }
}