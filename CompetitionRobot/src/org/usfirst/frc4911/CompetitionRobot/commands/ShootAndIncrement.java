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
public class ShootAndIncrement extends CommandGroup {
    public ShootAndIncrement(){
        addSequential(new Shoot());
        addSequential(new IncrementCatapult());
    }
}
