package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;

public class IncrementCatapult extends Command {
    ShooterSystem shooter = Robot.shooter;
    private double startTime;
    private static double GOAL_TIME = 0.10;

    public IncrementCatapult() {
        requires(Robot.shooter);
    }

    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
    }

    protected void execute() {
        shooter.rotate(1.0);
    }

    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime >= GOAL_TIME;
    }

    protected void end() {
        shooter.stop();
        //shooter.resetSensors();
    }

    protected void interrupted() {
    }
}