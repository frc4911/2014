package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc4911.CompetitionRobot.RobotConstants;
import org.usfirst.frc4911.CompetitionRobot.subsystems.CollectorSystem;
import org.usfirst.frc4911.CompetitionRobot.subsystems.ShooterSystem;
import org.usfirst.frc4911.CompetitionRobot.Robot;

public class CollectorDown extends Command {
    private CollectorSystem collectorSystem = Robot.collectorSystem;
    private ShooterSystem shooter = Robot.shooter;
    private double startTime;
    private static double GOAL_TIME = 3.0;
    
    public CollectorDown() {
        requires(Robot.collectorSystem);
        requires(Robot.shooter);
    }

    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
    }

    protected void execute() {
        collectorSystem.runAxle(1.0);//Right Direction??
    }

    protected boolean isFinished() {
        if(Timer.getFPGATimestamp() - startTime >= GOAL_TIME){
            System.out.println("FAIL SAFE KICKED IN FOR CollectorDown METHOD");
        }
        return (collectorSystem.getAngle() <= RobotConstants.COLLECTER_MIN_ANGLE) || (Timer.getFPGATimestamp() - startTime >= GOAL_TIME);
    }

    protected void end() {
        collectorSystem.stopAxle();
        shooter.COCKED = true;
    }

    protected void interrupted() {
    }
}