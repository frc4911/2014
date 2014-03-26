package org.usfirst.frc4911.CompetitionRobot.subsystems;

import org.usfirst.frc4911.CompetitionRobot.RobotMap;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RollerSystem extends Subsystem {
    private SpeedController CollectorWheelTalon = RobotMap.CollectorWheelTalon;

    public void initDefaultCommand() {
    }
    public void run(double power){
        CollectorWheelTalon.set(power);
    }
    public void runForwards(){
        CollectorWheelTalon.set(1.0);
    }
    public void runBackwards(){
        CollectorWheelTalon.set(-1.0);
    }
    public void stop(){
        CollectorWheelTalon.set(0.0);
    }
}

