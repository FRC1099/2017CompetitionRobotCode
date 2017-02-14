package org.usfirst.frc.team1099.robot.subsystems;

import org.usfirst.frc.team1099.robot.RobotMap;
import org.usfirst.frc.team1099.robot.commands.Shooter.StartShooterFast;
import org.usfirst.frc.team1099.robot.commands.Shooter.StartShooterIdle;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon shooterMotor = new CANTalon(RobotMap.SHOOTERMOTOR);	
	
	double lastTime = Timer.getFPGATimestamp();
	double totalAmpHours;
	
	public void initShooter() {
        setMotor(shooterMotor);
	}
    /**
     * This function is called periodically during operator control
     */
    public void startShooter(double speedSP) {
    	shooterMotor.setSetpoint(speedSP);
    	
    	
    	double voltage = shooterMotor.getOutputVoltage();
    	double current = shooterMotor.getOutputCurrent();
    	
    	double power = voltage * current;
    	
    	double newTime = Timer.getFPGATimestamp();
    	
    	double sampleTime = newTime - lastTime;
    	
    	double currentAmpHours = (sampleTime * current) / (3.6 * (Math.pow(10, 3)));
    	
    	lastTime = newTime;
    	
    	totalAmpHours = totalAmpHours + currentAmpHours;
    	
    	SmartDashboard.putNumber("Voltage", voltage);
    	SmartDashboard.putNumber("Current", current);
    	SmartDashboard.putNumber("Power", power);
    	SmartDashboard.putNumber("Total AMP Hours", totalAmpHours);
    	SmartDashboard.putNumber("Speed", shooterMotor.getSpeed());
    	SmartDashboard.putNumber("Set Point Speed", shooterMotor.getSetpoint());
    	SmartDashboard.putNumber("Sample Time", sampleTime);
    }
    
    public void setMotor(CANTalon m) {
    	
    	m.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	
    	m.configPeakOutputVoltage(+12.0f, -12.0f);
        
        /* set closed loop gains in slot0 */
        m.setProfile(0);

       m.setF(0.2);
       m.setP(0.2);
       m.setI(0.001);
       m.setD(0);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new StartShooterIdle());
    }
}

