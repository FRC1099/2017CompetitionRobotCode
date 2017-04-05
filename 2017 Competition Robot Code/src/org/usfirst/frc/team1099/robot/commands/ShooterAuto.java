package org.usfirst.frc.team1099.robot.commands;

import org.usfirst.frc.team1099.robot.commands.Drive.AutoDrive;
import org.usfirst.frc.team1099.robot.commands.Drive.AutoDriveEncoder;
import org.usfirst.frc.team1099.robot.commands.Drive.ChangeControlMode;
import org.usfirst.frc.team1099.robot.commands.Drive.ResetEncoderPosition;
import org.usfirst.frc.team1099.robot.commands.Shooter.StartShooterFast;
import org.usfirst.frc.team1099.robot.commands.Shooter.StartShooterIndexer;
import org.usfirst.frc.team1099.robot.commands.Shooter.StopShooter;
import org.usfirst.frc.team1099.robot.commands.Shooter.StopShooterIndexer;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShooterAuto extends CommandGroup {

	public static final int RED = 0;
	public static final int BLUE = 1;
	
	int alliance = 0;
	
    public ShooterAuto(int alliance) {
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
    	
    	
    	if( alliance == RED) {
    		addSequential(new AutoDrive(-.26, -.25), 2.0); // move away from wall (back up)
    	} else {
    		addSequential(new AutoDrive(-.26, -.25), 1.4); // move away from wall (back up)
    	}
    	
    	if( alliance == RED) {
    		addSequential(new AutoDrive(-.36, +.35), 1.0); // turn
    	} else {
    		addSequential(new AutoDrive(+.36, -.35), 0.6); // turn
    	}
    	
    	addParallel(new StartShooterFast(), 1.0); // move toward wall as shooter spins up
    	addSequential(new AutoDrive(.31, .30), 1.5); 
    	
    	addParallel(new AutoDrive(0.0, 0.0), 8.0);
    	addSequential(new StartShooterIndexer(), 8.0);
    	
    	
    	// shut down the shooter and drive away away
    	addParallel(new StopShooterIndexer(), 1.0);
    	addParallel(new StopShooter(), 1.0);
    	
    	
    	if( alliance == RED) {
    		addSequential(new AutoDrive(-.50, -.65), 3); // back away with slight turn
    	} else {
    		addSequential(new AutoDrive(-.65, -.50), 3); // back away with slight turn
    	}
    	
    	   	
    }
}
