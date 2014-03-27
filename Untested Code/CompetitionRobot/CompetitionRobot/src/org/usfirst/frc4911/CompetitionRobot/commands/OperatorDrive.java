package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.RobotConstants;
import org.usfirst.frc4911.CompetitionRobot.subsystems.*;

public class  OperatorDrive extends Command {
    DriveSystem driveSystem = Robot.driveSystem;
    CollectorSystem collectorSystem = Robot.collectorSystem;
    ShooterSystem shooterSystem = Robot.shooter;
   // RollerSystem rollerSystem = Robot.rollerSystem;
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
        double leftPow = 0;
        double rightPow = 0;
        double axlePow = 0;
        if(Math.abs(leftJoystick.getY()) >= 0.1) {
            leftPow = MathUtils.round(leftJoystick.getY() * RobotConstants.JOYSTICK_SENSITIVITY) / RobotConstants.JOYSTICK_SENSITIVITY;  
        }
        if(Math.abs(rightJoystick.getY()) >= 0.1) {
            rightPow = MathUtils.round(rightJoystick.getY() * RobotConstants.JOYSTICK_SENSITIVITY) / RobotConstants.JOYSTICK_SENSITIVITY;
        } 
        if(!usingDriveSystem){
            driveSystem.drive(-leftPow, -rightPow);        
        }
        
        //rollerSystem.run(paylo);
        
        /*
        if(Math.abs(payloadJoystick.getRawAxis(2)) >= 0.1) {
            //axlePow = payloadJoystick.getRawAxis(2);
            axlePow = MathUtils.round(payloadJoystick.getRawAxis(2) * RobotConstants.JOYSTICK_SENSITIVITY) / RobotConstants.JOYSTICK_SENSITIVITY;
        } 
        collectorSystem.runAxle(-axlePow);
        collectorSystem.runWheel(payloadJoystick.getRawAxis(3));
        */
        
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
}
