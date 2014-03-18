package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc4911.CompetitionRobot.Robot;
import org.usfirst.frc4911.CompetitionRobot.subsystems.CollectorSystem;

public class SetAndHold extends CommandGroup {
    
    public SetAndHold() {
        addParallel(new SetShooter());
        //addParallel(new HoldSetShooter());
    }
}