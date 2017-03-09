package org.usfirst.frc.team1099.robot.subsystems;

import org.usfirst.frc.team1099.robot.OI;
import org.usfirst.frc.team1099.robot.Robot;
import org.usfirst.frc.team1099.robot.RobotMap;
import org.usfirst.frc.team1099.robot.commands.Climber.StartClimbMotor;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Talon climberMotor = new Talon(RobotMap.CLIMBERMOTOR);
	
	public void startClimbMotor() {
		if(Robot.oi.getRightAxis() > 0.1 && OI.gamepad.getRawButton(RobotMap.CLIMBERDOWNVERIFY)) {
			climberMotor.set(1.0);
		}
		else if(Robot.oi.getRightAxis() < -0.1) {
			climberMotor.set(-1.0);
		}
		else {
			climberMotor.set(0);
		}
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new StartClimbMotor());
    }
}

