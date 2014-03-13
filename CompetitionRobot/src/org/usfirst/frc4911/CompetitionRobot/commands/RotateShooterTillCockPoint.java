package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.ShooterSystem;

public class RotateShooterTillCockPoint extends Command {
    private ShooterSystem shooter = Robot.shooter;
    private static final double DELTA_DEGREES = 265;
    private double startDegrees;
    
    public RotateShooterTillCockPoint() {
        requires(Robot.shooter);
    }

    protected void initialize() {
        startDegrees = shooter.getDegrees();
    }

    protected void execute() {
        shooter.rotate(1.0);
    }

    protected boolean isFinished() {
        return shooter.getDegrees() - startDegrees >= DELTA_DEGREES;
    }

    protected void end() {
        shooter.stop();
        shooter.resetSensors();
    }

    protected void interrupted() {
    }
}