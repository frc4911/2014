package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.RobotConstants;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;

public class PullBackwards extends Command {
    CollectorSystem collectorSystem = Robot.collectorSystem;

    public PullBackwards() {
        requires(Robot.collectorSystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        collectorSystem.runAxleBackwards();
        System.out.println("Pot Value:\t" + collectorSystem.getPotentiometer());
    }

    protected boolean isFinished() {
        return collectorSystem.getPotentiometer() >= RobotConstants.COLLECTER_MAX_VOLT;
    }

    protected void end() {
        collectorSystem.stopAxle();
        System.out.println("PullBackwards Ended");
    }

    protected void interrupted() {
    }
}