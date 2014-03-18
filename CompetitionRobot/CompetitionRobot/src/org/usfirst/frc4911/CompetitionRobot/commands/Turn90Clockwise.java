package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.DriveSystem;
import org.usfirst.frc4911.CompetitionRobot.subsystems.Sensors;

public class Turn90Clockwise extends Command{
    DriveSystem driveSystem = Robot.driveSystem;
    Sensors sensors = Robot.sensors;
    Command teleOp;
    private double goalAngle;
    private double startTime;
    private static final double TIME_THAT_TAKES_TO_SET = 1.0;
    
    public Turn90Clockwise(){
        requires(Robot.driveSystem);
        requires(Robot.sensors);  
    }
    
    protected void initialize(){
        teleOp = Robot.teleOp;
        goalAngle = sensors.getAngle() + 44.0;
        startTime = Timer.getFPGATimestamp();
        ((OperatorDrive)teleOp).setDriveSystemUsage(true);
    }
    
    protected void execute(){
        driveSystem.drive(1.0 , -1.0);
    }
    
    protected boolean isFinished(){  
        if((Timer.getFPGATimestamp() - startTime) >= TIME_THAT_TAKES_TO_SET){
            System.out.println("FAIL SAFE");
        }
        return (sensors.getAngle() >= goalAngle) || ((Timer.getFPGATimestamp() - startTime) >= TIME_THAT_TAKES_TO_SET) ;
    }
    
    protected void end(){
        System.out.println("Ending Angle:\t" + sensors.getAngle());
        ((OperatorDrive)teleOp).setDriveSystemUsage(false);
        sensors.reset();
    }
    
    protected void interrupted(){    
        System.out.println("Clockwise Interrupted===============================");
    }
}
