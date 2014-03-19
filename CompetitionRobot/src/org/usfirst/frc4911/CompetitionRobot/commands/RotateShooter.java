package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;

public class RotateShooter extends Command {
    ShooterSystem shooter = Robot.shooter;
    
    public RotateShooter() {
        requires(Robot.shooter);
    }

    protected void initialize() {
    }

    protected void execute() {
        shooter.rotate(0.65);
        //FOR DEBUGGING USE ONLY
        //System.out.println("Encoder:\t" + shooter.getDegrees());
    }

    protected boolean isFinished() {
        return Robot.oi.payloadButton2.get();
    }

    protected void end() {
        shooter.stop();
        //FOR DEBUGGING USE ONLY
        //System.out.println("Encoder:\t" + shooter.getDegrees());
        shooter.resetSensors();
    }

    protected void interrupted() {
    }
}