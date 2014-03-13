package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SetShooterWithLimitSwitch extends CommandGroup {
    
    public SetShooterWithLimitSwitch() {
        addSequential(new RotateShooterTillCockPoint());
        addSequential(new RotateShooterTillSwitch());
    }
}