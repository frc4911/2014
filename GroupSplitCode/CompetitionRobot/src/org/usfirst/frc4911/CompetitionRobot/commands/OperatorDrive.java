package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
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
    PneumaticCollectorSystem pneumaticCollectorSystem = Robot.pneumaticCollectorSystem;
    RollerSystem rollerSysetm = Robot.rollerSystem;
    ShooterSystem shooterSystem = Robot.shooter;
    Sensors sensors = Robot.sensors;
    Joystick leftJoystick = Robot.oi.getLeftJoy();
    Joystick rightJoystick = Robot.oi.getRightJoy();
    Joystick payloadJoystick = Robot.oi.getPayloadJoy();
    double prevTime = 0.0;
    double time = 0.0;
    
    private boolean usingDriveSystem;
    
    DigitalInput digitalInput;
    
    public OperatorDrive() {
    }

    protected void initialize() {
        sensors.reset();
        shooterSystem.resetSensors();//If autonomous is Successful and the shooter must be at 0
        shooterSystem.setOffset(42.0);
        shooterSystem.statusInitialize();//If autonomous is Successful and the shooter must be at 0
        usingDriveSystem = false;
        
        //digitalInput = new DigitalInput(6);
    }

    protected void execute() {
        double leftPow = 0;
        double rightPow = 0;
        if(Math.abs(leftJoystick.getY()) >= 0.1) {
            leftPow = MathUtils.round(leftJoystick.getY() * RobotConstants.JOYSTICK_SENSITIVITY) / RobotConstants.JOYSTICK_SENSITIVITY;  
        }
        if(Math.abs(rightJoystick.getY()) >= 0.1) {
            rightPow = MathUtils.round(rightJoystick.getY() * RobotConstants.JOYSTICK_SENSITIVITY) / RobotConstants.JOYSTICK_SENSITIVITY;
        } 
        if(!usingDriveSystem){
            driveSystem.drive(-leftPow, -rightPow);        
        }
        
        rollerSysetm.run(payloadJoystick.getRawAxis(3));
        if(payloadJoystick.getRawAxis(2) >= 0.8){
            pneumaticCollectorSystem.in();
        } else if(payloadJoystick.getRawAxis(2) <= -0.8){
            pneumaticCollectorSystem.out();
        } else if(payloadJoystick.getRawAxis(6) >= 0.8){
            pneumaticCollectorSystem.in();
        } else if(payloadJoystick.getRawAxis(6) <= -0.8){
            pneumaticCollectorSystem.out();
        }
        //double currTime = Timer.getFPGATimestamp();
        time = Timer.getFPGATimestamp();
        System.out.println("\t\tTIME:\t" + time);
        System.out.println("Cycle Time:\t" + (time - prevTime));
        prevTime = time;
        //System.out.println("DriveTrain:\t" + (-leftPow) + "\t" + (-rightPow));
        System.out.println("Encoder:\t" + sensors.getLeftDistance() + "\t" + sensors.getRightDistance());
        //System.out.println("Collector:\t" + (payloadJoystick.getRawAxis(2)) + "\tWheel:\t" + (payloadJoystick.getRawAxis(3)));
        //System.out.println("Gyro:\t" + sensors.getAngle());     
        System.out.println("Cocked:\t" + shooterSystem.COCKED);  
        System.out.println("Switch:\t" + shooterSystem.getSwitch());
        System.out.println("Shooter Encoder:\t" + shooterSystem.getDegrees());
        System.out.println("Catapult:\t" + shooterSystem.getDegrees());
        System.out.println("Offset:\t" + shooterSystem.getOffset());
        //System.out.println("New Switch:\t" + digitalInput.get());
        System.out.println("Potentiometer:\t" + pneumaticCollectorSystem.getPot());
                        //System.out.println("Ultrasonic:\t" + sensors.getUltrasonic());
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
