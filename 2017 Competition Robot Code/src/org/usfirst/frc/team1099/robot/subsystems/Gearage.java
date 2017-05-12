package org.usfirst.frc.team1099.robot.subsystems;

import org.usfirst.frc.team1099.robot.RobotMap;
import org.usfirst.frc.team1099.robot.Gearage.GearageIn;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gearage extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	DoubleSolenoid gearPickup = new DoubleSolenoid(RobotMap.GEARIN, RobotMap.GEAROUT);
	
	public void gearIn() {
		gearPickup.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void gearOut() {
		gearPickup.set(DoubleSolenoid.Value.kForward);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

