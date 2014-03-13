package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import com.sun.squawk.util.MathUtils;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.RobotConstants;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;

public class  OperatorDrive extends Command {
    DriveSystem driveSystem = Robot.driveSystem;
    CollectorSystem collectorSystem = Robot.collectorSystem;
    ShooterSystem shooterSystem = Robot.shooter;
    Sensors sensors = Robot.sensors;
    Joystick leftJoystick = Robot.oi.getLeftJoy();
    Joystick rightJoystick = Robot.oi.getRightJoy();
    Joystick payloadJoystick = Robot.oi.getPayloadJoy();
    
    private boolean usingDriveSystem;
    
    public OperatorDrive() {        
    }

    protected void initialize() {
        sensors.reset();
        shooterSystem.resetSensors();//If autonomous is Successful and the shooter must be at 0
        shooterSystem.statusInitialize();//If autonomous is Successful and the shooter must be at 0
        usingDriveSystem = false;
    }

    protected void execute() {
        if(!usingDriveSystem){
            driveSystem.drive(-renderInput(leftJoystick.getY()), -renderInput(rightJoystick.getY()));        
        }
        collectorSystem.runAxle(-renderInput(payloadJoystick.getY()));
        collectorSystem.runWheel(payloadJoystick.getRawAxis(3));
        
        /*
        System.out.println("\t\tTIME:\t" + Timer.getFPGATimestamp());
        System.out.println("DriveTrain:\t" + (-leftPow) + "\t" + (-rightPow));
        System.out.println("Encoder:\t" + sensors.getLeftDistance() + "\t" + sensors.getRightDistance());
        System.out.println("Collector:\t" + (payloadJoystick.getRawAxis(2)) + "\tWheel:\t" + (payloadJoystick.getRawAxis(3)));
        System.out.println("Potentiometer:\t" + collectorSystem.getPotentiometer() + "\tAngle:\t" + collectorSystem.getAngle());
        System.out.println("Gyro:\t" + sensors.getAngle());     
        System.out.println("Cocked:\t" + shooterSystem.COCKED);  
        System.out.println("Switch:\t" + shooterSystem.getSwitch());
        System.out.println("Ultrasonic:\t" + sensors.getUltrasonic());
        System.out.println("=====================================================================");
        */
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        this.cancel();
    }
    
    protected void interrupted() {
    }
    
    public boolean getDriveSystemUsage(){
        return usingDriveSystem;
    }
    public void setDriveSystemUsage(boolean bool){
        usingDriveSystem = bool;
    }
    public double renderInput(double value){
        double returnValue = 0.0;
        if(Math.abs(value) >= 0.1) {
            returnValue = MathUtils.round(value * RobotConstants.JOYSTICK_SENSITIVITY) / RobotConstants.JOYSTICK_SENSITIVITY;  
        }
        return returnValue;
    }
}
