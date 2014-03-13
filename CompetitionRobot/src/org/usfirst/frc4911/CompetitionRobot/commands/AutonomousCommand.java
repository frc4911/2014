package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class  AutonomousCommand extends Command {

    public AutonomousCommand() { 
        new AutonomousTest().start();
    }

    protected void initialize() {
    }

    protected void execute() { 
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
        this.cancel();
    }

    protected void interrupted() {        
    }
}
