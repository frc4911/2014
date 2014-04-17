package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc4911.CompetitionRobot.subsystems.ShooterSystem;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.RobotConstants;

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
        if(RobotConstants.DEBUG_SWITCH) {
            System.out.println("Shooting Catapult:\t" + shooter.getDegrees());
            System.out.println("Catapult Status:\t" + shooter.COCKED);
            System.out.println("Catapult Switch:\t" + shooter.getSwitch());
            System.out.println("\n\n\n\n\n============================================");
        }
    }

    protected boolean isFinished() {
        currentTime = Timer.getFPGATimestamp();
        //FOR DEBUGGING USE ONLY
        if(RobotConstants.DEBUG_SWITCH) {
            if((currentTime - startTime) >= TIME_THAT_TAKES_TO_SET){
                System.out.println("============================Timed Out=====================\n\n\n\n\n\n");
            }
        }
        
        //double currentTime = Timer.getFPGATimestamp();
        //cycleTime = currentTime - prevTime;
        //prevTime = currentTime;
        //double predictedError = cycleTime * shooter.getRate(); //Method getRate() must be created
        //return (predictedError + shooter.getDegrees() >= shooter.GOAL_ANGLE) || (Timer.getFPGATimestamp() - startTime >= TIME_THAT_TAKES_TO_SET);
        //
        
        
        cycleTime =  currentTime - prevTime;
        prevTime = currentTime;
        predictedError = COCK_ANGLE / ( 996.9 / cycleTime);//700 / cycleTime gives fraction of the distance traveled
        error = shooter.GOAL_ANGLE - shooter.getDegrees();
        return    (shooter.getDegrees() >= shooter.GOAL_ANGLE) 
               || (0.5 * predictedError >= error) 
               || (currentTime - startTime >= TIME_THAT_TAKES_TO_SET);
        //OLD CODE//return (shooter.getDegrees() >= shooter.GOAL_ANGLE) || ((Timer.getFPGATimestamp() - startTime) >= TIME_THAT_TAKES_TO_SET);        
    }

    protected void end() {
        shooter.stop();
        shooter.COCKED = true;
        if(RobotConstants.DEBUG_SWITCH) {
            System.out.println("\n\n\n\n\n\n");
            System.out.println("Total Time To COCK:\t" + (currentTime - startTime));
            System.out.println("Position after COCKING:\t" + shooter.getDegrees());
            System.out.println("\n\n\n\n\n\n");
        }
    }

    protected void interrupted() {
    }
}
