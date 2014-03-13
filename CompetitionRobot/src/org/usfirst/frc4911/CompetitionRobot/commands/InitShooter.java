package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;

public class InitShooter extends Command {
    ShooterSystem shooter = Robot.shooter;
    public double startTime;
    public static final double GOAL_TIME = 2.0;
    public static final double DELTA_DEGREES = 265;
    
    public InitShooter() {
        requires(Robot.shooter);
    }

    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
    }

    protected void execute() {
        shooter.rotate(0.65);
    }

    protected boolean isFinished() {
        return (shooter.getSwitch()) || (Timer.getFPGATimestamp() - startTime >= GOAL_TIME);
    }

    protected void end() {
        shooter.stop();
        shooter.resetSensors();
        
        //move certain degrees
        double switchDegrees = shooter.getDegrees();
        while(shooter.getDegrees() - switchDegrees <= DELTA_DEGREES){
            System.out.println(shooter.getDegrees() - switchDegrees);
            shooter.rotate(0.65);
        }
        shooter.stop();
        shooter.resetSensors();
        shooter.COCKED = true;
    }

    protected void interrupted() {
    }
}