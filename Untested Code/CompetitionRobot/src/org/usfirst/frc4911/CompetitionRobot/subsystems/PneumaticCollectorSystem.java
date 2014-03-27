package org.usfirst.frc4911.CompetitionRobot.subsystems;

import org.usfirst.frc4911.CompetitionRobot.RobotMap;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticCollectorSystem extends Subsystem {
    private Compressor compressor = RobotMap.Compressor;
    private Solenoid In = RobotMap.PneumaticCollectorIn;
    private Solenoid Out = RobotMap.PneumaticCollectorOut;
    
    public PneumaticCollectorSystem(){
        start();
    }
    public void initDefaultCommand() {
    }
    public void start(){
        compressor.start();
    }
    public void stop(){
        compressor.stop();
    }
    public void in(){
        Out.set(false);
        In.set(true);
        
    }
    public void out(){
        In.set(false);
        Out.set(true);
    }
    
}

