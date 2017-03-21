package org.usfirst.frc.team1099.robot.subsystems;

import org.usfirst.frc.team1099.robot.OI;
import org.usfirst.frc.team1099.robot.Robot;
import org.usfirst.frc.team1099.robot.RobotMap;
import org.usfirst.frc.team1099.robot.commands.Drive.TeleDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {

	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	PowerDistributionPanel pdp = new PowerDistributionPanel();
	double lastTime = Timer.getFPGATimestamp();
	double totalAmpHours;
	
	CANTalon ldrive = new CANTalon(RobotMap.LCANDRIVE);
	CANTalon lslave1 = new CANTalon(RobotMap.LCANSLAVE1);
	CANTalon lslave2 = new CANTalon(RobotMap.LCANSLAVE2);
	
	CANTalon rdrive = new CANTalon(RobotMap.RCANDRIVE);
	CANTalon rslave1 = new CANTalon(RobotMap.RCANSLAVE1);
	CANTalon rslave2 = new CANTalon(RobotMap.RCANSLAVE2);
	
	DoubleSolenoid shift = new DoubleSolenoid(RobotMap.SHIFTHIGH, RobotMap.SHIFTLOW);
	
	double getPositionTest;
	
	double getPositionLive;
	
	public Drivetrain() {

		lslave1.changeControlMode(TalonControlMode.Follower);
		lslave1.set(RobotMap.LCANDRIVE);
		
		lslave2.changeControlMode(TalonControlMode.Follower);
		lslave2.set(RobotMap.LCANDRIVE);
		
		rslave1.changeControlMode(TalonControlMode.Follower);
		rslave1.set(RobotMap.RCANDRIVE);
		
		rslave2.changeControlMode(TalonControlMode.Follower);
		rslave2.set(RobotMap.RCANDRIVE);
		
		ldrive.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		rdrive.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);

		
		// goToSpeedControl();
		goToVoltageControl();
		
		ldrive.set(0);
		rdrive.set(0);
		
	}

	public void goToSpeedControl() {
		ldrive.changeControlMode(TalonControlMode.Speed);
		rdrive.changeControlMode(TalonControlMode.Speed);
		
		ldrive.set(0);
		rdrive.set(0);
		
		ldrive.setF(0.0);
		ldrive.setP(.4);
		ldrive.setI(0);
		ldrive.setD(0);

		rdrive.setF(0.0);
		rdrive.setP(.4);
		rdrive.setI(0);
		rdrive.setD(0);
	}
	
	public void goToVoltageControl() {
		ldrive.changeControlMode(TalonControlMode.PercentVbus);
		rdrive.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	public void drive() {
		double pdp0Current = pdp.getCurrent(0);
		double pdp1Current = pdp.getCurrent(1);
		double pdp2Current = pdp.getCurrent(2);
		double pdp15Current = pdp.getCurrent(15);
		double pdp14Current = pdp.getCurrent(14);
		double pdp13Current = pdp.getCurrent(13);
		
		double totalCurrent = pdp0Current + pdp1Current + pdp2Current + pdp15Current + pdp14Current + pdp13Current;
		
		double newTime = Timer.getFPGATimestamp();
		double sampleTime = newTime - Robot.shooter.lastTime;
		
		double currentAmpHours = (sampleTime * totalCurrent) / (3.6 * (Math.pow(10, 3)));
    	
    	lastTime = newTime;
    	
    	totalAmpHours = totalAmpHours + currentAmpHours;
		
		double left = OI.lstick.getRawAxis(1);
		double right = OI.rstick.getRawAxis(1);
		
		if(OI.lstick.getRawButton(RobotMap.SLOWDRIVEBUTTON)) {
			left = left * 0.5;
			right = right * 0.5;
		}
		
		ldrive.set(-left);
		rdrive.set(right);
		
		SmartDashboard.putNumber("PDP 0 Current", pdp0Current);
		SmartDashboard.putNumber("PDP 1 Current", pdp1Current);
		SmartDashboard.putNumber("PDP 2 Current", pdp2Current);
		SmartDashboard.putNumber("PDP 15 Current", pdp15Current);
		SmartDashboard.putNumber("PDP 14 Current", pdp14Current);
		SmartDashboard.putNumber("PDP 13 Current", pdp13Current);
		SmartDashboard.putNumber("Total Current", totalCurrent);
		SmartDashboard.putNumber("Drive Total AMP Hours", totalAmpHours);
		SmartDashboard.putBoolean("Shift Status", shiftStatus);
		
		ldrive.getPosition();
	}
	
	public void autoDrive(double leftSpeed, double rightSpeed) {
		ldrive.set(-leftSpeed);
		rdrive.set(rightSpeed);
	}
	
	public int getLeftEncoderPosition() {
		return -ldrive.getEncPosition();
	}
	
	public int getRightEncoderPosition() {
		return rdrive.getEncPosition();
	}
	
	public double getLeftPosition(){
		return -ldrive.getPosition();
	}
	
	public double getRightPosition() {
		return rdrive.getPosition();
	}
	
	private static final boolean HI = true;
	private static final boolean LO = false;
	private boolean shiftStatus = LO;
	
	public void shiftHigh() {
		shift.set(Value.kForward);
		shiftStatus = HI;
	}
	
	public void shiftLow() {
		shift.set(Value.kReverse);
		shiftStatus = LO;
	}
	
	public boolean getShiftStatus() {
		return shiftStatus;
	}
	
	public void resetPosition() {
		ldrive.setEncPosition(0);
		rdrive.setEncPosition(0);
		
		ldrive.setPosition(0);
		rdrive.setPosition(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TeleDrive());
    }
}

