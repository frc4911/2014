package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;

public class InitShooter extends Command {
    ShooterSystem shooter = Robot.shooter;
    public double startTime;
    public static final double GOAL_TIME = 5.0;
    public static final double DELTA_DEGREES = 275; //TEST THIS VALUE
    public boolean hitLimitSwitch;
    public boolean isCocked;
    double limitSwitchEncoder;
    public static final double GOAL_TIME_2 = 5.0;
    
    public InitShooter() {
        requires(Robot.shooter);
    }

    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
        hitLimitSwitch = false;
        isCocked = false;
        shooter.setOffset(0.0);
        limitSwitchEncoder = 0.0;
    }

    protected void execute() {
        if(hitLimitSwitch && shooter.getDegrees() - limitSwitchEncoder <= (DELTA_DEGREES/2)) {
            shooter.rotate(1.0);
        }
        else {
            shooter.rotate(0.65);
        }
        System.out.println();
    }

    protected boolean isFinished() {
        if(!hitLimitSwitch) {
            hitLimitSwitch = (shooter.getSwitch() || Timer.getFPGATimestamp() - startTime >= GOAL_TIME);
            limitSwitchEncoder = shooter.getDegrees();
            startTime = Timer.getFPGATimestamp();
        }
        else {
            double dist = shooter.getDegrees() - limitSwitchEncoder;
            isCocked = (dist >= DELTA_DEGREES || Timer.getFPGATimestamp() - startTime >= GOAL_TIME_2);
        }
        return isCocked;
    }

    protected void end() {
        shooter.stop();
        shooter.resetSensors();
        shooter.COCKED = true;        
    }

    protected void interrupted() {
    }
}