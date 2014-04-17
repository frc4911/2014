package org.usfirst.frc4911.CompetitionRobot.subsystems;

import org.usfirst.frc4911.CompetitionRobot.RobotMap;
import org.usfirst.frc4911.CompetitionRobot.commands.IdleTime;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveSystem extends Subsystem {
    private SpeedController rearLeftTalon = RobotMap.RearLeftTalon;
    private SpeedController frontLeftTalon = RobotMap.FrontLeftTalon;
    private SpeedController rearRightTalon = RobotMap.RearRightTalon;
    private SpeedController frontRightTalon = RobotMap.FrontRightTalon;

    public void initDefaultCommand() {
        setDefaultCommand(new IdleTime());
    }
    
    public void drive(double leftPower, double rightPower){
        Watchdog.getInstance().feed();
        rearLeftTalon.set(leftPower);
        frontLeftTalon.set(leftPower);
        rearRightTalon.set(-rightPower);
        frontRightTalon.set(-rightPower);        
    }
        
    public void stop(){
        drive(0.0 , 0.0);
    }
    
    public void runRearLeft(){
        rearLeftTalon.set(0.1);
    }
    public void runFrontLeft(){
        frontLeftTalon.set(0.1);
    }
    public void runRearRight(){
        rearRightTalon.set(0.1);
    }
    public void runFrontRight(){
        frontRightTalon.set(0.1);
    }
    public void runRight(double power){
        frontRightTalon.set(-power);
        rearRightTalon.set(-power);
    }
    public void runLeft(double power){
        frontLeftTalon.set(power);
        rearLeftTalon.set(power);
    }
}

