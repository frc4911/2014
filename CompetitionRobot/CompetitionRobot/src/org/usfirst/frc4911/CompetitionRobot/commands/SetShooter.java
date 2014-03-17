package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4911.CompetitionRobot.subsystems.ShooterSystem;
import org.usfirst.frc4911.CompetitionRobot.Robot;

public class SetShooter extends Command {
    ShooterSystem shooter = Robot.shooter;   
    public static final double TIME_THAT_TAKES_TO_SET = 2.0;//Seconds
    public double power;
    public double startTime;
    
    public SetShooter() {
        requires(Robot.shooter);
    }

    protected void initialize() {
        power = 0.0;
        startTime = Timer.getFPGATimestamp();
        this.setInterruptible(false);
        if(shooter.COCKED){
            shooter.GOAL_ANGLE += 0.0;
        } else {
            shooter.GOAL_ANGLE += 300.0;
        }
    }

    protected void execute() {
        power = 0.0;
        if(!shooter.COCKED) power = 1.0;
        shooter.rotate(power);
        
    }

    protected boolean isFinished() {
        if((Timer.getFPGATimestamp() - startTime) >= TIME_THAT_TAKES_TO_SET){
            System.out.println("FAIL SAFE");
        }
        return (shooter.getDegrees() >= shooter.GOAL_ANGLE) || ((Timer.getFPGATimestamp() - startTime) >= TIME_THAT_TAKES_TO_SET);
        //return (shooter.getSwitch()) || ((Timer.getFPGATimestamp() - startTime) >= TIME_THAT_TAKES_TO_SET);
    }

    protected void end() {
        shooter.stop();
        System.out.println("SETSHOOTER ENDED");
        //shooter.resetSensors();//If using a switch
        shooter.COCKED = true;
    }

    protected void interrupted() {
    }
}
