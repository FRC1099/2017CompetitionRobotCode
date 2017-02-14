package org.usfirst.frc.team1099.robot.subsystems;

import org.usfirst.frc.team1099.robot.RobotMap;
import org.usfirst.frc.team1099.robot.commands.Intake.IntakeStop;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallIntake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Talon intakeMotor = new Talon(RobotMap.INTAKEMOTOR);
	
	public void intakeIn() {
		//intakeMotor.set(0.75);
		intakeMotor.set(0.5);
	}
	
	public void intakeOut() {
		//intakeMotor.set(-0.75);
		intakeMotor.set(-0.5);
	}
	
	public void intakeStop() {
		intakeMotor.set(0);
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeStop());
    }
}

