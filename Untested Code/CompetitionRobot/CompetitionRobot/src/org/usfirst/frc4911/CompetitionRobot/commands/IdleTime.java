package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.DriveSystem;
import org.usfirst.frc4911.CompetitionRobot.subsystems.Sensors;

public class IdleTime extends Command {
    DriveSystem driveSystem = Robot.driveSystem;
    Sensors sensors = Robot.sensors;
    
    public IdleTime() {
        requires(Robot.driveSystem);
        requires(Robot.sensors);
        
    }
    
    protected void initialize() {
        driveSystem.stop();
        sensors.reset();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}