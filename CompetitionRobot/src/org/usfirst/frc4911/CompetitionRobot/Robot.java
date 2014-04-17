package org.usfirst.frc4911.CompetitionRobot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc4911.CompetitionRobot.commands.*;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;

public class Robot extends IterativeRobot {
    public static Command autonomousCommand;
    public static Command teleOp;

    public static OI oi;
    public static DriveSystem driveSystem;
    public static Sensors sensors;
    public static ShooterSystem shooter;
    public static PneumaticCollectorSystem pneumaticCollectorSystem;
    public static RollerSystem rollerSystem;

    public void robotInit() {
	RobotMap.init();
        driveSystem = new DriveSystem();
        sensors = new Sensors();
        shooter = new ShooterSystem();
        pneumaticCollectorSystem = new PneumaticCollectorSystem();
        rollerSystem = new RollerSystem();
        oi = new OI();// This MUST be here.	
        autonomousCommand = new AutonomousCommand();
        teleOp = new OperatorDrive();     
    }

    public void autonomousInit() {
        if (teleOp != null) {
            teleOp.cancel();
        }
        if (autonomousCommand != null) {
            autonomousCommand = new AutonomousCommand();
            shooter.setStatus(true);//Set the status of the Shooter to Fired
        }
        pneumaticCollectorSystem.start();
        //shooter.resetSensors();
        shooter.setStatus(true);
        autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) {
            autonomousCommand.cancel();     
        }
        pneumaticCollectorSystem.start();
        //new InitShooter().start();
        shooter.setOffset(40.0);
        teleOp.start();
        //shooter.resetSensors();
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
        //shooter.statusInitialize();
        //shooter.resetSensors();
        autonomousCommand = new AutonomousCommand();
        teleOp = new OperatorDrive();
        Scheduler.getInstance().removeAll();
        //pneumaticCollectorSystem.start();
        
        if(RobotConstants.DEBUG_SWITCH) {
            System.out.println("Ready To Roll Out!");
        }
    }
    
    public void disabledPeriodic(){
        sensors.reset();
        
    }
    
}
