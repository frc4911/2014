package org.usfirst.frc4911.CompetitionRobot.commands;

import org.usfirst.frc4911.CompetitionRobot.RobotMap;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.Timer;

public class AutonomousTest extends CommandGroup {
    
    public  AutonomousTest() {           
        /*
        
        addSequential(new CollectorDown());//3 seconds
         
        addParallel(new RunWheelIn());//5 seconds
        addSequential(new DriveStraight(36.0));//no fail safe but should be less than 2 seconds
        
        addParallel(new SetShooter());        
        addSequential(new CollectorUp());3 seconds
        addSequential(new CollectorDown());3 seconds
        addSequential(new Shoot());1 seconds
        */
        
        
        //addSequential(new InitShooter());
        addSequential(new DriveStraight(36.0));
        //Timer.delay(1.0);
        addSequential(new CollectorDown());        
        addSequential(new Shoot());
    }
    
}
