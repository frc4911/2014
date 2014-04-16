/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Payton
 */
public class AutoFire extends CommandGroup{
    public AutoFire() {
        addParallel(new SurgeRollerIn(0.65));
        addSequential(new PneumaticCollectorDown());
        addSequential(new Shoot());
    }
    /*public AutoFire() {
        PneumaticCollectorDown cmd1 = new PneumaticCollectorDown();
        addSequential(cmd1);
        addSequential(new Shoot(cmd1));
    }*/
}

