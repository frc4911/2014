package org.usfirst.frc4911.CompetitionRobot.commands;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4911.CompetitionRobot.RobotConstants;
import org.usfirst.frc4911.CompetitionRobot.subsystems.DriveSystem;
import org.usfirst.frc4911.CompetitionRobot.subsystems.Sensors;
import org.usfirst.frc4911.CompetitionRobot.Robot;

public class  DriveStraight extends Command {
    DriveSystem driveSystem = Robot.driveSystem;
    Sensors sensors = Robot.sensors;
    
    private double kp;
    private double goalDistance;
    //private double currentDistance;
    private double error;
    private double power;
    
    private double startTime;
    
    
    public DriveStraight(double goal) {
        requires(Robot.driveSystem);   
        requires(Robot.sensors);
        goalDistance = goal;
    }

    protected void initialize() {
        driveSystem.stop();
        sensors.reset();
        kp = RobotConstants.DRIVESTRAIGHT_CORRECTION_CONSTANT;
        error = 0.0;
        power = 0.0;
        startTime = Timer.getFPGATimestamp();
        System.out.println("INITIALIZED");
    }

    protected void execute() {
        error = kp * sensors.getAngle();
        power = getRampedPower(goalDistance, sensors.getDistance());            
        driveSystem.drive(power - error , power + error);  
        
        //driveSystem.drive(0.8, 0.8);
        
        //System.out.println("Encoders:\t" + sensors.getLeftDistance() + "\t" + sensors.getRightDistance() + "\tPower:\t" + power);
        //System.out.println("Encoders:\t" + sensors.getDistance() + "\tPower:\t" + power);
    }

    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime >= 1.0;
        //return (sensors.getDistance() >= goalDistance);
    }

    protected void end() {
        driveSystem.stop();
        System.out.println("ENDED");
        Timer.delay(3.0);
    }

    protected void interrupted() {
    }
    
    private double getRampedPower(double goalDistance, double currentDistance){
        double fractionOfGoalDistance = Math.min(currentDistance / goalDistance, 1.0);        
        double rampedPower = RobotConstants.AMPLITUDE * MathUtils.pow(Math.cos(0.5 * Math.PI * fractionOfGoalDistance) , RobotConstants.RAMP_UP) * MathUtils.pow(fractionOfGoalDistance , RobotConstants.RAMP_DOWN);
        rampedPower = Math.min(rampedPower + RobotConstants.FLOOR, RobotConstants.CEILING);
        return rampedPower;
    }
}
