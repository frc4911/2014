package org.usfirst.frc4911.CompetitionRobot.subsystems;

import org.usfirst.frc4911.CompetitionRobot.RobotMap;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc4911.CompetitionRobot.RobotConstants;

public class CollectorSystem extends Subsystem {
    private SpeedController CollectorAxeleTalon = RobotMap.CollectorAxeleTalon;
    private SpeedController CollectorWheelTalon = RobotMap.CollectorWheelTalon;
    private AnalogPotentiometer Potentiometer = RobotMap.CollectorPotentiometer;
   

    public void initDefaultCommand() {
        //setDefaultCommand(new IdleTime());
    }
    public double getPotentiometer(){
        return Potentiometer.get();
    }
    public double getAngle(){
        return (Potentiometer.get() - RobotConstants.POT_MIN_VOLT) / RobotConstants.VD;
    }
    
    public boolean reachedMaxAngle() {
        return getAngle() > RobotConstants.COLLECTER_MAX_ANGLE;
    }
    
    public boolean reachedMinAngle() {
        return getAngle() < RobotConstants.COLLECTER_MIN_ANGLE;
    }
    
    public void runWheel(double power){
        CollectorWheelTalon.set(power);
    }
    public void runWheelForwards(){
        CollectorWheelTalon.set(1.0);
    }
    public void runWheelBackwards(){
        CollectorWheelTalon.set(-1.0);
    }
    public void stopWheel(){
        CollectorWheelTalon.set(0.0);
    }
    
    public void runAxle(double power){
        CollectorAxeleTalon.set(power);
    }
    public void runAxleForwards(){
        CollectorAxeleTalon.set(1.0);
    }
    public void runAxleBackwards(){
        CollectorAxeleTalon.set(-1.0);
    }
    public void stopAxle(){
        CollectorAxeleTalon.set(0.0);
    }
}

