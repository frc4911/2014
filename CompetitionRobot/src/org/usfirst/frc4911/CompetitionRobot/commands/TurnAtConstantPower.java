package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4911.CompetitionRobot.subsystems.DriveSystem;
import org.usfirst.frc4911.CompetitionRobot.subsystems.Sensors;
import org.usfirst.frc4911.CompetitionRobot.Robot;

public class TurnAtConstantPower extends Command {
    DriveSystem driveSystem = Robot.driveSystem;
    Sensors sensors = Robot.sensors;
    private double goalPower;
    
    public TurnAtConstantPower(double goal) {
        requires(Robot.driveSystem);
        requires(Robot.sensors);
        goalPower = goal;
    }

    protected void initialize() {
        driveSystem.stop();
        sensors.reset();
    }

    protected void execute() {
        driveSystem.drive(goalPower, -goalPower);  
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        //System.out.println("Angles Turned:\t" + sensors.getAngle() + "\tdegrees");
    }

    protected void interrupted() {
    }
}