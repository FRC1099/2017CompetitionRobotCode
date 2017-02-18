package org.usfirst.frc.team1099.robot.subsystems;

import org.usfirst.frc.team1099.robot.RobotMap;
import org.usfirst.frc.team1099.robot.commands.Shooter.StartShooterFast;
import org.usfirst.frc.team1099.robot.commands.Shooter.StartShooterIdle;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon shooter = new CANTalon(RobotMap.SHOOTERCAN);	
	Talon shooterIndexerMotor = new Talon(RobotMap.SHOOTERINDEXMOTOR);
	
	Joystick stick = new Joystick(0);	
	
	double lastTime = Timer.getFPGATimestamp();
	
	double totalAmpHours;
	
	public void initShooter() {
        /* first choose the sensor */
        setMotor(shooter);
        
    	shooter.reverseOutput(false);
    	shooter.reverseSensor(false); 
        shooter.setInverted(false);
        shooter.changeControlMode(TalonControlMode.Speed);
	}
    /**
     * This function is called periodically during operator control
     */
    public void startShooter(double speedSP) {
    	shooter.set(speedSP);
    	
    	double voltage = shooter.getOutputVoltage();
    	double current = shooter.getOutputCurrent();
    	
    	double power = voltage * current;
    	
    	double newTime = Timer.getFPGATimestamp();
    	
    	double sampleTime = newTime - lastTime;
    	
    	double currentAmpHours = (sampleTime * current) / (3.6 * (Math.pow(10, 3)));
    	
    	lastTime = newTime;
    	
    	totalAmpHours = totalAmpHours + currentAmpHours;
    	
//    	SmartDashboard.putNumber("Voltage", voltage);
    	SmartDashboard.putNumber("Voltage", shooter.getOutputVoltage());
    	SmartDashboard.putNumber("Current", shooter.getOutputCurrent());
    	SmartDashboard.putNumber("Power", power);
    	SmartDashboard.putNumber("Total AMP Hours", totalAmpHours);
    	SmartDashboard.putNumber("Speed", -shooter.getSpeed());
    	//SmartDashboard.putNumber("Set Point Speed", shooter.getSetpoint());
    	SmartDashboard.putNumber("Set Point Speed", speedSP);
    	SmartDashboard.putNumber("Sample Time", sampleTime);
    	SmartDashboard.putNumber("Closed Loop Error", shooter.getClosedLoopError());
    }
    
    private void setMotor(CANTalon m) {
    	
    	m.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	
    	m.configNominalOutputVoltage(+0.0f, -0.0f);
    	m.configPeakOutputVoltage(+12.0f, -12.0f);
        
        /* set closed loop gains in slot0 */
        m.setProfile(0);

       m.setF(0.03); // 0.2
       m.setP(0.01); // 0.2
       m.setI(0.0001); // 0.001
       m.setD(0.0);
    }
    
    public void startShooterIndexer() {
    	shooterIndexerMotor.set(0.25);
    	
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new StartShooterIdle());
    }
}

