package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc4911.CompetitionRobot.subsystems.ShooterSystem;
import org.usfirst.frc4911.CompetitionRobot.Robot;

public class SetShooter extends Command {
    ShooterSystem shooter = Robot.shooter;   
    private static final double TIME_THAT_TAKES_TO_SET = 2.0;//Seconds
    private static final int COCK_ANGLE = 320;
    private double power;
    private double startTime;
    
    private double prevTime;
    private double currentTime;
    private double cycleTime;
    private double predictedError;
    private double error;
    
    
    public SetShooter() {
        requires(Robot.shooter);
    }

    protected void initialize() {
        power = 0.0;
        startTime = Timer.getFPGATimestamp();
        this.setInterruptible(false);
        //if(shooter.COCKED){
        //    shooter.GOAL_ANGLE += 0.0;
        //}else {
        if(!shooter.COCKED) {
            shooter.GOAL_ANGLE += COCK_ANGLE;
        }
        prevTime = startTime;
    }

    protected void execute() {
        power = 0.0;
        if(!shooter.COCKED) {
            power = 1.0;
        }
        shooter.rotate(power);
        System.out.println("Setting Catapult:\t" + shooter.getDegrees());
    }

    protected boolean isFinished() {
        
        /*//FOR DEBUGGING USE ONLY
        if((Timer.getFPGATimestamp() - startTime) >= TIME_THAT_TAKES_TO_SET){
            System.out.println("FAIL SAFE");
        }*/
        
        //double currentTime = Timer.getFPGATimestamp();
        //cycleTime = currentTime - prevTime;
        //prevTime = currentTime;
        //double predictedError = cycleTime * shooter.getRate(); //Method getRate() must be created
        //return (predictedError + shooter.getDegrees() >= shooter.GOAL_ANGLE) || (Timer.getFPGATimestamp() - startTime >= TIME_THAT_TAKES_TO_SET);
        //
        
        currentTime = Timer.getFPGATimestamp();
        cycleTime =  currentTime - prevTime;
        prevTime = currentTime;
        predictedError = COCK_ANGLE / ( 700.0 / cycleTime);//700 / cycleTime gives fraction of the distance traveled
        error = shooter.GOAL_ANGLE - shooter.getDegrees();
        return (shooter.getDegrees() >= shooter.GOAL_ANGLE) || (0.5 * predictedError >= error) || (currentTime - startTime >= TIME_THAT_TAKES_TO_SET);
        //OLD CODE//return (shooter.getDegrees() >= shooter.GOAL_ANGLE) || ((Timer.getFPGATimestamp() - startTime) >= TIME_THAT_TAKES_TO_SET);        
    }

    protected void end() {
        shooter.stop();
        shooter.COCKED = true;
        System.out.println("\n\n\n\n\n\n");
        System.out.println("Total Time To COCK:\t" + (currentTime - startTime));
        System.out.println("Position after COCKING:\t" + shooter.getDegrees());
        System.out.println("\n\n\n\n\n\n");
    }

    protected void interrupted() {
    }
}
