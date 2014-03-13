package org.usfirst.frc4911.CompetitionRobot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4911.CompetitionRobot.commands.*;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;

//The VM is configured to automatically run this class
public class Robot extends IterativeRobot {
    public static Command autonomousCommand;
    public static Command teleOp;

    public static OI oi;
    public static DriveSystem driveSystem;
    public static Sensors sensors;
    public static ShooterSystem shooter;    
    public static CollectorSystem collectorSystem;

    public void robotInit() {
	RobotMap.init();
        driveSystem = new DriveSystem();
        sensors = new Sensors();
        shooter = new ShooterSystem();
        //cameraSystem = new CameraSystem();
        collectorSystem = new CollectorSystem();
        oi = new OI();// This MUST be here.	
        autonomousCommand = new AutonomousCommand();
        teleOp = new OperatorDrive();
    }

    public void autonomousInit() {        
        shooter.setStatus(true);//Set the status of the Shooter to Fired
        autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        teleOp.start();
    }
    
    public void teleopPeriodic() {
        Scheduler.getInstance().run();               
    }

    public void testInit(){
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void disabledInit(){
        sensors.reset();
        shooter.statusInitialize();
        shooter.resetSensors();
        autonomousCommand = new AutonomousCommand();
        teleOp = new OperatorDrive();
        Scheduler.getInstance().removeAll();
        System.out.println("Ready To Roll Out!");
    }
    
    public void disabledPeriodic(){
        sensors.reset();
    }
    
}
