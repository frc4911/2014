package org.usfirst.frc4911.CompetitionRobot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;
import org.usfirst.frc4911.CompetitionRobot.RobotConstants;
import org.usfirst.frc4911.CompetitionRobot.RobotMap;

public class ShooterSystem extends Subsystem {
    private SpeedController ShooterTalon = RobotMap.ShooterTalon;
    private Encoder ShooterEncoder = RobotMap.ShooterEncoder;
    private DigitalInput Switch = RobotMap.Switch;
    
    public static double GOAL_ANGLE;
    public static boolean COCKED;
    
    public ShooterSystem(){
        this.resetSensors();
        this.statusInitialize();
    }
    
    public void initDefaultCommand() {
    }
    
    public boolean getSwitch(){
        return !Switch.get();
    }
    public void resetSensors() {
        ShooterEncoder.reset();
        ShooterEncoder.setDistancePerPulse(RobotConstants.DEGREES_PER_PULSE);        
        GOAL_ANGLE = 0;
    }
    public void statusInitialize(){
        COCKED = false;
    }
    
    public void setStatus(boolean cocked){
        COCKED = cocked;
    }
    
    public double getDegrees() {
        return ShooterEncoder.getDistance();
    }
    
    public void rotate(double power) {
        power = Math.max(power, 0.0);
        ShooterTalon.set(power);
    }
    
    public void stop() {
        ShooterTalon.set(0.0);
    }
}
