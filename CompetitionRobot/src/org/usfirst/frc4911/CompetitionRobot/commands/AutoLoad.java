package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.*;

/**
 *
 * @author Elijah
 */
public class AutoLoad extends CommandGroup {
    
    public AutoLoad() {
        addParallel(new SurgeRollerIn(1.0)); 
        addParallel(new PneumaticCollectorUp());
        addSequential(new SetShooter());
        
    }
}
