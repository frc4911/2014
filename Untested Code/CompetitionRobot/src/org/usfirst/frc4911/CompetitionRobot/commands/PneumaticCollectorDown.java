package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;

public class PneumaticCollectorDown extends Command {
    private PneumaticCollectorSystem collector = Robot.pneumaticCollectorSystem;
    

    public PneumaticCollectorDown() {
        requires(Robot.pneumaticCollectorSystem);
    }

    protected void initialize() {
        collector.out();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}