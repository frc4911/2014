package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.*;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;

/**
 *
 * @author Elijah
 */
public class SurgeRollerOut extends Command {
    private RollerSystem roller = Robot.rollerSystem;
    private double startTime;
    private double goalTime;
    
    public SurgeRollerOut(double seconds) {
        requires(Robot.rollerSystem);
        this.goalTime = seconds;
    }

    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
        goalTime += startTime;
    }

    protected void execute() {
        roller.runForwards();
    }

    protected boolean isFinished() {
        return (Timer.getFPGATimestamp() >= goalTime);
    }

    protected void end() {
        roller.stop();
    }

    protected void interrupted() {
    }
}
