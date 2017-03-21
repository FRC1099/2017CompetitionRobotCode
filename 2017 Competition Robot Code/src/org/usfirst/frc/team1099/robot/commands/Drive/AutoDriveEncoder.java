package org.usfirst.frc.team1099.robot.commands.Drive;

import org.usfirst.frc.team1099.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveEncoder extends Command {

	static int i = 0;
	static int j = 0;
	int ltarget = 4096;
	int rtarget = 4096;
	double lspeed = 0;
	double rspeed = 0;

    public AutoDriveEncoder() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    public AutoDriveEncoder(double lspeed, double rspeed, int lt, int rt) {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	init( lspeed, rspeed, lt, rt);
    }

public AutoDriveEncoder(double lspeed, double rspeed, double distance) {
	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);

		// distance is in inches
    	int target = (int)( distance * ( 1 / 24.9 ) * ( 24 / 15.0 ) * 4096);
    	init(lspeed, rspeed, target, target);
    }

	private void init(double lspeed, double rspeed, int ltarget, int rtarget) {
		this.lspeed = lspeed;
    	this.rspeed = rspeed;
    	this.ltarget = ltarget;
    	this.rtarget = rtarget;
    	
    	SmartDashboard.putNumber("Calculated target", ltarget);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.drivetrain.resetPosition();
    	SmartDashboard.putNumber("Init Encoder Position", Robot.drivetrain.getLeftEncoderPosition());
    	SmartDashboard.putNumber("J", j++);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        int lpos=Robot.drivetrain.getLeftEncoderPosition();
        int rpos=Robot.drivetrain.getRightEncoderPosition();
                
        Robot.drivetrain.autoDrive(lspeed, rspeed);
    	
        /*
        if(Math.abs(lpos) > ltarget) {
        	Robot.drivetrain.autoDrive(0, rspeed);
        }
        
        if(Math.abs(rpos) > rtarget) {
        	Robot.drivetrain.autoDrive(lspeed, 0);
        }
        */
        
    	SmartDashboard.putNumber("Left Encoder Position", Robot.drivetrain.getLeftEncoderPosition());
    	SmartDashboard.putNumber("Right Encoder Position", Robot.drivetrain.getRightEncoderPosition());
    	SmartDashboard.putNumber("I", i++);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        int lpos=Robot.drivetrain.getLeftEncoderPosition();
        int rpos=Robot.drivetrain.getRightEncoderPosition();
        
        // return Math.abs(lpos) > ltarget && Math.abs(rpos) > rtarget;
        return Math.abs(lpos) > ltarget;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.autoDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
