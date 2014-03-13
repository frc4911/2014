package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.ShooterSystem;

public class RotateShooterTillSwitch extends Command {
    ShooterSystem shooter = Robot.shooter;

    protected void initialize() {
    }

    protected void execute() {
        shooter.rotate(1.0);
    }

    protected boolean isFinished() {
        return shooter.getSwitch();
    }

    protected void end() {
        shooter.stop();
        shooter.resetSensors();
    }

    protected void interrupted() {
    }

}