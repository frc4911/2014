package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.*;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;
import org.usfirst.frc4911.CompetitionRobot.OI;
import org.usfirst.frc4911.CompetitionRobot.RobotConstants;

/**
 *
 * @author Elijah
 */
public class SurgeRollerIn extends Command {
    private RollerSystem roller = Robot.rollerSystem;
    private double startTime;
    private double goalTime;
    private OI operator;
    private double goalTimeDelta;
    
    //new teleop object for roller conflict
    Command teleop;

    public SurgeRollerIn(double seconds) {
        requires(Robot.rollerSystem);
        //goalTime = seconds;
        goalTimeDelta = seconds;
    }

    protected void initialize() {
        teleop = Robot.teleOp;
        operator = Robot.oi;
        startTime = Timer.getFPGATimestamp();
        goalTime = startTime + goalTimeDelta;
        //goalTime = goalTime + startTime;
        //setting roller system boolean
        ((OperatorDrive)teleop).setRollerSystemUsage(true);
        if(RobotConstants.DEBUG_SWITCH) {
            System.out.println("Start Time:\t" + startTime);
            System.out.println("Goal Time:\t" + goalTime);
        }
    }

    protected void execute() {
        if(RobotConstants.DEBUG_SWITCH) {
            System.out.println("=====================================================");
            System.out.println("Start Time:\t" + startTime);
            System.out.println("Curr Time:\t" + Timer.getFPGATimestamp());
            System.out.println("Goal Time:\t" + goalTime);
            System.out.println("=====================================================");
        }
       roller.runForwards();
    }

    protected boolean isFinished() {
        return (Timer.getFPGATimestamp() >= goalTime || operator.getPayloadJoy().getRawAxis(3) <= -0.5 
                || operator.getPayloadJoy().getRawAxis(3) >= 0.5);
    }

    protected void end() {
        roller.stop();
        //setting roller system boolean
        ((OperatorDrive)teleop).setRollerSystemUsage(false);
        if(RobotConstants.DEBUG_SWITCH) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("DONE AUTOFIRE!");
            System.out.println();
            System.out.println();
            System.out.println();
        }
        
        
    }

    protected void interrupted() {
    }
}
