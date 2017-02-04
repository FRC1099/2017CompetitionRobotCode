package org.usfirst.frc.team1099.robot.subsystems;

import org.usfirst.frc.team1099.robot.OI;
import org.usfirst.frc.team1099.robot.Robot;
import org.usfirst.frc.team1099.robot.RobotMap;
import org.usfirst.frc.team1099.robot.commands.Drive.TeleDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon ldrive = new CANTalon(RobotMap.LCANDRIVE);
	CANTalon lslave1 = new CANTalon(RobotMap.LCANSLAVE1);
	CANTalon lslave2 = new CANTalon(RobotMap.LCANSLAVE2);
	
	CANTalon rdrive = new CANTalon(RobotMap.RCANDRIVE);
	CANTalon rslave1 = new CANTalon(RobotMap.RCANSLAVE1);
	CANTalon rslave2 = new CANTalon(RobotMap.RCANSLAVE2);
	
	DoubleSolenoid shift = new DoubleSolenoid(RobotMap.SHIFTHIGH, RobotMap.SHIFTLOW);
	
	public void drive() {
		lslave1.changeControlMode(TalonControlMode.Follower);
		lslave1.set(0);
		
		lslave2.changeControlMode(TalonControlMode.Follower);
		lslave2.set(0);
		
		rslave1.changeControlMode(TalonControlMode.Follower);
		rslave1.set(3);
		
		rslave2.changeControlMode(TalonControlMode.Follower);
		rslave2.set(3);
		
		double left = OI.lstick.getRawAxis(1);
		double right = OI.rstick.getRawAxis(1);
		
		if(OI.lstick.getTrigger()) {
			left = left * 0.5;
			right = right * 0.5;
		}
		
		ldrive.set(left);
		rdrive.set(right);
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

