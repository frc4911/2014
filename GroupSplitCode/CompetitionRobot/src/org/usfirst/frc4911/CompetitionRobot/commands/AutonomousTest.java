package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousTest extends CommandGroup {
    
    public  AutonomousTest() {      
        addSequential(new DriveStraight(36.0));
        addSequential(new PneumaticCollectorDown());        
        addSequential(new Shoot());
        //addSequential(new Delay(0.5));
        //addSequential(new InitShooter());
        //addSequential(new IncrementCatapult());
        //addSequential(new ShootAndIncrement());
    }
    
}
