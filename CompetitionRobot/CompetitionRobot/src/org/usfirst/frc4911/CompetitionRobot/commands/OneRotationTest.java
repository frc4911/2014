package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.RobotConstants;
import org.usfirst.frc4911.CompetitionRobot.subsystems.Sensors;
import org.usfirst.frc4911.CompetitionRobot.subsystems.DriveSystem;

public class OneRotationTest extends Command {
    DriveSystem driveSystem = Robot.driveSystem;
    Sensors sensors = Robot.sensors;
    
    private double currentDistance;
    
    public OneRotationTest() {
        requires(Robot.driveSystem);
        requires(Robot.sensors);
    }

    protected void initialize() {
        sensors.reset();
        driveSystem.stop();
        currentDistance = 0.0;
        System.out.println("OneRotationTest initialize");
    }

    protected void execute() {
        Watchdog.getInstance().feed();
        driveSystem.drive(0.15, 0.15);
        currentDistance = sensors.getDistance();
        System.out.println("Distance:\t" + sensors.getLeftDistance() + "\t" + sensors.getRightDistance() + "\tcurrentDistance:\t" + sensors.getDistance());
    }

    protected boolean isFinished() {
        return currentDistance >= RobotConstants.ONE_ROTATION_IN_INCHES;
    }

    protected void end() {
        driveSystem.stop();
        System.out.println("Distance:\t" + currentDistance + "\tInches+++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    protected void interrupted() {
    }
}