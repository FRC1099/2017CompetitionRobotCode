package org.usfirst.frc.team1099.robot.commands;

import org.usfirst.frc.team1099.robot.commands.Drive.AutoDrive;
import org.usfirst.frc.team1099.robot.commands.Drive.AutoDriveEncoder;
import org.usfirst.frc.team1099.robot.commands.Drive.AutoDriveStraight;
import org.usfirst.frc.team1099.robot.commands.Drive.ChangeControlMode;
import org.usfirst.frc.team1099.robot.commands.Drive.ResetEncoderPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftGearPeg extends CommandGroup {

	public LeftGearPeg() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel. 

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new ChangeControlMode(ChangeControlMode.SPEED), .5);
    	
    	// forward
    	addSequential(new ResetEncoderPosition(),0.2);
    	//rpm, rpm, inches
    	addSequential(new AutoDriveEncoder(125, 125, 50.0), 4);
    	addSequential(new AutoDriveEncoder(0, 0, 0),0.5);
    	
    	// turn 45 CW
    	addSequential(new ResetEncoderPosition(),0.2);
    	//rpm, rpm, teeth
    	addSequential(new AutoDriveEncoder(125, -125, 1500, 1500 ), 2);
    	
    	// forward to peg
    	addSequential(new ResetEncoderPosition(),0.2);
    	addSequential(new AutoDriveEncoder(125, 125, 36.0), 2);
      }
}
