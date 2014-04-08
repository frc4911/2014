package org.usfirst.frc4911.CompetitionRobot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc4911.CompetitionRobot.commands.*;

public class OI {
    public Joystick rightJoy;
    public Joystick leftJoy;
    public Joystick payloadJoy;
    
    public JoystickButton rightButton1;
    public JoystickButton leftButton1;
    
    public JoystickButton payloadButton1;
    public JoystickButton payloadButton2;
    public JoystickButton payloadButton3;
    public JoystickButton payloadButton4;
    public JoystickButton payloadButton5;
    public JoystickButton payloadButton6;
    public JoystickButton payloadButton7;
    public JoystickButton payloadButton8;

    public OI() {
        rightJoy = new Joystick(RobotConstants.JOYSTICK_RIGHT);
        leftJoy = new Joystick(RobotConstants.JOYSTICK_LEFT);
        payloadJoy = new Joystick(RobotConstants.JOYSTICK_PAYLOAD);
        
        rightButton1 = new JoystickButton(rightJoy, 1);
        rightButton1.whenPressed(new Turn90Clockwise());
        
        leftButton1 = new JoystickButton(leftJoy, 1);
        leftButton1.whenPressed(new Turn90CounterClockwise());
        
        payloadButton1 = new JoystickButton(payloadJoy, 1);
        payloadButton1.whenPressed(new Shoot());
        
        payloadButton3 = new JoystickButton(payloadJoy, 3);
        payloadButton3.whenPressed(new AutoFire());
        
        payloadButton4 = new JoystickButton(payloadJoy, 4);
        payloadButton4.whenPressed(new InitShooter());
        
        payloadButton7 = new JoystickButton(payloadJoy, 7);
        payloadButton7.whenPressed(new IncrementCatapult());
        
        payloadButton2 = new JoystickButton(payloadJoy, 2);
        payloadButton2.whenPressed(new SetShooter());
    }
    
    public Joystick getLeftJoy() {
        return leftJoy;
    }

    public Joystick getRightJoy() {
        return rightJoy;
    }
    public Joystick getPayloadJoy(){
        return payloadJoy;
    }
}

