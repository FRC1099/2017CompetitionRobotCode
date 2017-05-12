package org.usfirst.frc.team1099.robot.commands.Shooter;

import org.usfirst.frc.team1099.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartShooterFast extends Command {

    public StartShooterFast() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.initShooter();
    }

    int speed = 0;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.shooterStatus = "FAST";
    	
    	Robot.shooter.startShooter(-(speed));
    	
    	if (speed < 1620) speed += 50;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	speed = 0;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
