package org.usfirst.frc.team1099.robot.subsystems;

import org.usfirst.frc.team1099.robot.OI;
import org.usfirst.frc.team1099.robot.Robot;
import org.usfirst.frc.team1099.robot.RobotMap;
import org.usfirst.frc.team1099.robot.commands.Drive.TeleDrive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon drive1 = new CANTalon(RobotMap.CANDRIVE1);
	CANTalon drive2 = new CANTalon(RobotMap.CANDRIVE2);
	CANTalon drive3 = new CANTalon(RobotMap.CANDRIVE3);
	CANTalon drive4 = new CANTalon(RobotMap.CANDRIVE4);
	CANTalon drive5 = new CANTalon(RobotMap.CANDRIVE5);
	CANTalon drive6 = new CANTalon(RobotMap.CANDRIVE6);
	
	DoubleSolenoid shift = new DoubleSolenoid(RobotMap.SHIFTHIGH, RobotMap.SHIFTLOW);
	
	public void drive() {
		double left = OI.lstick.getRawAxis(1);
		double right = OI.rstick.getRawAxis(1);
		
		if(OI.lstick.getTrigger()) {
			left = left * 0.5;
			right = right * 0.5;
		}
		
		drive1.set(left);
		drive2.set(left);
		drive3.set(left);
		drive4.set(right);
		drive5.set(right);
		drive6.set(right);
	}
	
	public void shiftHigh() {
		shift.set(Value.kForward);
	}
	
	public void shiftLow() {
		shift.set(Value.kReverse);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TeleDrive());
    }
}

