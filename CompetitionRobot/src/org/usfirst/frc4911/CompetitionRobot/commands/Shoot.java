package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.OI;
import org.usfirst.frc4911.CompetitionRobot.RobotConstants;
import org.usfirst.frc4911.CompetitionRobot.subsystems.CollectorSystem;
import org.usfirst.frc4911.CompetitionRobot.subsystems.ShooterSystem;

public class Shoot extends Command {
    CollectorSystem collecterSystem = Robot.collectorSystem;
    ShooterSystem shooter = Robot.shooter;
    public static final double TIME_THAT_TAKES_TO_SHOOT = 1.0;//Seconds
    public double startTime;
    public double power;
    
    public Shoot() {
        requires(Robot.shooter);
    }

    protected void initialize() {
        this.setInterruptible(false);
        if(shooter.COCKED){
            shooter.GOAL_ANGLE += 60.0;
        } else {
            shooter.GOAL_ANGLE += 0.0;//300.0 + 60.0;
        }
        power = 0.0;
        startTime = Timer.getFPGATimestamp();
    }

    protected void execute() {
        shooter.rotate(1.0);//0.65
        
        /*
        if(collecterSystem.getPotentiometer() <= RobotConstants.COLLECTER_MIN_VOLT) {
            if(shooter.COCKED)power = 1.0;
            shooter.rotate(power);
        }
        else {
            collecterSystem.runAxleForwards();
        }*/
    }

    protected boolean isFinished() {
        return (shooter.getDegrees() >= shooter.GOAL_ANGLE) || (Timer.getFPGATimestamp() - startTime >= TIME_THAT_TAKES_TO_SHOOT);
    }

    protected void end() {
        shooter.stop();
        shooter.COCKED = false;
    }
    
    protected void interrupted() {
    }
}