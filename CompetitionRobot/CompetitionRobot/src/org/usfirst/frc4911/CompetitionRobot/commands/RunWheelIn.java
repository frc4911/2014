package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;

public class RunWheelIn extends Command {
    private CollectorSystem collectorSystem = Robot.collectorSystem;
    private double startTime;
    private final double GOAL_TIME = 5.0;//seconds

    public RunWheelIn() {
        requires(Robot.collectorSystem);
    }

    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
    }

    protected void execute() {
        collectorSystem.runWheel(1.0);//Direction??
    }

    protected boolean isFinished() {
        return (Timer.getFPGATimestamp() - startTime >= GOAL_TIME);
    }

    protected void end() {
        collectorSystem.stopWheel();
    }

    protected void interrupted() {
    }
}