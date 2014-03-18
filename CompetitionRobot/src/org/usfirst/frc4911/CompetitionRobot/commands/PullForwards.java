package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.RobotConstants;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;

public class PullForwards extends Command {
    CollectorSystem collectorSystem = Robot.collectorSystem;

    public PullForwards() {
        requires(Robot.collectorSystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        collectorSystem.runAxleForwards();
        System.out.println("Pot Value:\t" + collectorSystem.getPotentiometer());
    }

    protected boolean isFinished() {
        return collectorSystem.getPotentiometer() <= RobotConstants.COLLECTER_MIN_VOLT;
    }

    protected void end() {
        collectorSystem.stopAxle();
        System.out.println("PullForwards Ended");
    }

    protected void interrupted() {
    }
}