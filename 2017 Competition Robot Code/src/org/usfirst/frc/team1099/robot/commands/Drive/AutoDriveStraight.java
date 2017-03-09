package org.usfirst.frc.team1099.robot.commands.Drive;

import org.usfirst.frc.team1099.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveStraight extends Command {

	private double speed;
	private double adjSpeed;
	private int count = 0;
	private double leftPos;
	private double rightPos;
	
    public AutoDriveStraight(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	
    	this.speed = speed;
    	adjSpeed = speed;
    	leftPos = Robot.drivetrain.getLeftEncoderPosition();
    	rightPos = Robot.drivetrain.getRightEncoderPosition();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.autoDrive(speed,adjSpeed);
    	double oldLeftPos;
    	double oldRightPos;
    	double dLeftPos;
    	double dRightPos;
    	double error = 0.01;
    	double adjFactor = 0.02;
    	if(count >= 1){
    		oldLeftPos = leftPos;
    		oldRightPos = rightPos;
    		leftPos = Robot.drivetrain.getLeftEncoderPosition();
        	rightPos = Robot.drivetrain.getRightEncoderPosition();
        	dLeftPos = Math.abs(leftPos - oldLeftPos);
    		dRightPos = Math.abs(rightPos - oldRightPos);
    		if( dLeftPos > dRightPos ){ // left is faster
        		adjSpeed = speed  + adjFactor; // more V to the right side
        	}
        	else {
        		adjSpeed = speed - adjFactor; 
        	}
    	}
    	else{
    		leftPos = Robot.drivetrain.getLeftEncoderPosition();
    		rightPos = Robot.drivetrain.getRightEncoderPosition();
    	}
    	count++;
    	
    	
    	SmartDashboard.putNumber("Left Pos Auto", leftPos);
    	SmartDashboard.putNumber("Right Pos Auto", rightPos);
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
