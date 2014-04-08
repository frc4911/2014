package org.usfirst.frc4911.CompetitionRobot.subsystems;

import org.usfirst.frc4911.CompetitionRobot.RobotMap;
import org.usfirst.frc4911.CompetitionRobot.RobotConstants;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Sensors extends Subsystem{
    private Encoder leftEncoder = RobotMap.LeftEncoder;
    private Encoder rightEncoder = RobotMap.RightEncoder;
    private Gyro gyro = RobotMap.Gyro;
    private AnalogChannel ultrasonic = RobotMap.UltraSonicSensor;

    public void initDefaultCommand() {
        reset();
    }
    
    public void resetGyro(){
        gyro.reset();
        gyro.setSensitivity(RobotConstants.GYRO_SENSITIVITY);
    }
    
    public void resetEncoders(){
        leftEncoder.start();
        leftEncoder.reset();
        leftEncoder.setDistancePerPulse(RobotConstants.ENCODER_DISTANCE_PER_PULSE);
        
        rightEncoder.start();
        rightEncoder.reset();
        rightEncoder.setDistancePerPulse(RobotConstants.ENCODER_DISTANCE_PER_PULSE);   
    }
    
    public double getUltrasonic(){
        return ultrasonic.getVoltage() / RobotConstants.vPerI * 12.0;
    }
    
    public void reset(){
        resetGyro();
        resetEncoders();
    }
    
    public Gyro getGyro(){
        return gyro;
    }
    
    public Encoder getLeftEncoder(){
        return leftEncoder;
    }
    
    public Encoder getRightEncoder(){
        return rightEncoder;
    }
    
    public double getAngle(){
        return gyro.getAngle();
    }
    
    public double getDistance(){
        return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
    }
    
    public double getLeftDistance(){
        return leftEncoder.getDistance();
    }
    
    public double getRightDistance(){
        return rightEncoder.getDistance();
    }
}
