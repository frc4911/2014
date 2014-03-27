package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.ShooterSystem;

public class Shoot extends Command {
    ShooterSystem shooter = Robot.shooter;
    public static final double TIME_THAT_TAKES_TO_SHOOT = 1.0;//Seconds
    private static final int FIRE_DELTA = 40;
    public double startTime;
    public double power;
    //private PneumaticCollectorDown priorCmd;
    
    public Shoot() {
        requires(Robot.shooter);
    }
    
    /*public Shoot(PneumaticCollectorDown priorCmd) {
        this.priorCmd = priorCmd;
    }*/

    protected void initialize() {
        this.setInterruptible(false);
        if(shooter.COCKED){
            shooter.GOAL_ANGLE += FIRE_DELTA;
        }
        //} else {
        //    shooter.GOAL_ANGLE += 0.0;//300.0 + 60.0;
        //}
        power = 0.0;
        startTime = Timer.getFPGATimestamp();
    }

    protected void execute() {
        power = 0.0;
        if(!shooter.COCKED) {
            power = 1.0;
        }
        shooter.rotate(power);//0.65
        System.out.println("Shooting Catapult:\t" + shooter.getDegrees());
    }

    protected boolean isFinished() {
        return (shooter.getDegrees() >= shooter.GOAL_ANGLE) || (Timer.getFPGATimestamp() - startTime >= TIME_THAT_TAKES_TO_SHOOT);
    }

    protected void end() {
        shooter.stop();
        shooter.COCKED = false;
        System.out.println("Post Shoot Catapult:\t" + shooter.getDegrees());
    }
    
    protected void interrupted() {
    }
}