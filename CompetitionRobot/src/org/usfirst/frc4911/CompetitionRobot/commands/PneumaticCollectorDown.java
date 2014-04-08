package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;

public class PneumaticCollectorDown extends Command {
    private PneumaticCollectorSystem collector = Robot.pneumaticCollectorSystem;
    private double startTime;
    

    public PneumaticCollectorDown() {
        requires(Robot.pneumaticCollectorSystem);
    }

    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
        collector.out();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return (Timer.getFPGATimestamp() - startTime) > 1.0;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}