package org.usfirst.frc4911.CompetitionRobot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class  AutonomousCommand extends Command {

    public AutonomousCommand() {
    }

    protected void initialize() {
        new AutonomousTest().start();
    }

    protected void execute() {        
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        this.cancel();
    }

    protected void interrupted() {
    }
}
