package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;

public class InitShooter extends Command {
    ShooterSystem shooter = Robot.shooter;
    public double startTime;
    public static final double GOAL_TIME = 5.0;
    public static final double DELTA_DEGREES = 280;
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
        limitSwitchEncoder = 0.0;
    }

    protected void execute() {
        shooter.rotate(0.65);
        System.out.println("Encoder:\t" + shooter.getDegrees());
    }

    protected boolean isFinished() {
        //return (shooter.getSwitch()) || (Timer.getFPGATimestamp() - startTime >= GOAL_TIME);
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
        System.out.println("Encoder:\t" + shooter.getDegrees());
        shooter.resetSensors();
        shooter.COCKED = true;
        
        /*//move certain degrees
        double switchDegrees = shooter.getDegrees();
        while(shooter.getDegrees() - switchDegrees <= DELTA_DEGREES){
            System.out.println(shooter.getDegrees() - switchDegrees);
            shooter.rotate(0.65);
        }
        shooter.stop();
        shooter.resetSensors();
        shooter.COCKED = true;*/
    }

    protected void interrupted() {
    }
}