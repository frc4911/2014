package org.usfirst.frc4911.CompetitionRobot;
    
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType; 
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.camera.AxisCamera;

public class RobotMap {
    public static SpeedController RearLeftTalon;
    public static SpeedController FrontLeftTalon;
    public static SpeedController RearRightTalon;
    public static SpeedController FrontRightTalon;
    public static SpeedController ShooterTalon;
    public static SpeedController CollectorAxeleTalon;
    public static SpeedController CollectorWheelTalon;
    public static Encoder LeftEncoder;
    public static Encoder RightEncoder;
    public static Encoder ShooterEncoder;
    public static AnalogPotentiometer CollectorPotentiometer;
    public static Gyro Gyro;
    
    public static Solenoid Solenoid;
    public static AxisCamera Camera;
    public static DigitalInput Switch;
    public static AnalogChannel UltraSonicSensor;
    
    public static Compressor Compressor;
    public static Solenoid PneumaticCollectorIn;
    public static Solenoid PneumaticCollectorOut;
    
    public static void init() {
        RearLeftTalon = new Talon(RobotConstants.REAR_LEFT_TALON_PORT);
        FrontLeftTalon = new Talon(RobotConstants.FRONT_LEFT_TALON_PORT);
        RearRightTalon = new Talon(RobotConstants.REAR_RIGHT_TALON_PORT);
        FrontRightTalon = new Talon(RobotConstants.FRONT_RIGHT_TALON_PORT);
        
        ShooterTalon = new Talon(RobotConstants.SHOOTER_TALON_PORT);
        
        CollectorAxeleTalon = new Talon(RobotConstants.COLLECTOR_AXELE_TALON_PORT_LEFT);
        CollectorWheelTalon = new Talon(RobotConstants.COLLECTOR_WHEEL_TALON_PORT);
        
        LeftEncoder = new Encoder(RobotConstants.LEFT_ENCODER_PORT_A, RobotConstants.LEFT_ENCODER_PORT_B, false, EncodingType.k4X);
        LeftEncoder.setDistancePerPulse(RobotConstants.ENCODER_DISTANCE_PER_PULSE);
        LeftEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        LeftEncoder.start();
        
        RightEncoder = new Encoder(RobotConstants.RIGHT_ENCODER_PORT_A, RobotConstants.RIGHT_ENCODER_PORT_B, false, EncodingType.k4X);
        RightEncoder.setDistancePerPulse(RobotConstants.ENCODER_DISTANCE_PER_PULSE);
        RightEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        RightEncoder.start();
        
        ShooterEncoder = new Encoder(RobotConstants.SHOOTER_ENCODER_PORT_A, RobotConstants.SHOOTER_ENCODER_PORT_B, false, EncodingType.k4X);
        ShooterEncoder.setDistancePerPulse(RobotConstants.ENCODER_DISTANCE_PER_PULSE);
        ShooterEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        ShooterEncoder.start();
        
        Gyro = new Gyro(RobotConstants.MAIN_GYRO_PORT);
        Gyro.setSensitivity(RobotConstants.GYRO_SENSITIVITY);
        
        CollectorPotentiometer = new AnalogPotentiometer(RobotConstants.COLLECTOR_POTENTIOMETER_CHANNEL);

        Switch = new DigitalInput(RobotConstants.SWITCH_PORT);
        
        UltraSonicSensor = new AnalogChannel(RobotConstants.ULTRASONIC_SENSOR_PORT);
        
        Compressor = new Compressor(RobotConstants.PNEUMATIC_COLLECTOR_SWITCH_CHANNEL_PORT, RobotConstants.PNEUMATIC_COLLECTOR_RELAY_CHANNEL_PORT);
        PneumaticCollectorIn = new Solenoid(RobotConstants.PNEUMATIC_COLLECTOR_IN);
        PneumaticCollectorOut = new Solenoid(RobotConstants.PNEUMATIC_COLLECTOR_OUT);
    }
}
