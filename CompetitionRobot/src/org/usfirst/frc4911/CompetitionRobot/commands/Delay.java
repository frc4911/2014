package org.usfirst.frc4911.CompetitionRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Payton
 */
public class Delay extends Command {
    
    double seconds;
    double startTime;
    
    public Delay(double seconds) {
        this.seconds = seconds;
        startTime = Timer.getFPGATimestamp();
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return (Timer.getFPGATimestamp() - startTime >= seconds);
    }

    protected void end() {
        
    }

    protected void interrupted() {
    }
}