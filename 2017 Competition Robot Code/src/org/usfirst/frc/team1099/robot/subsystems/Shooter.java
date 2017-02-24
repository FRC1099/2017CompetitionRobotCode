package org.usfirst.frc.team1099.robot.subsystems;

import org.usfirst.frc.team1099.robot.RobotMap;
import org.usfirst.frc.team1099.robot.commands.Shooter.StartShooterFast;
import org.usfirst.frc.team1099.robot.commands.Shooter.StartShooterIdle;
import org.usfirst.frc.team1099.robot.commands.Shooter.StopShooterIndexer;

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
	CANTalon shooter1 = new CANTalon(RobotMap.SHOOTERCAN1);	
	CANTalon shooter2 = new CANTalon(RobotMap.SHOOTERCAN2);
	Talon shooterIndexerMotor = new Talon(RobotMap.SHOOTERINDEXMOTOR);
	CANTalon shooterAgitator = new CANTalon(RobotMap.SHOOTERAGITATORCAN);
	
	Joystick stick = new Joystick(0);	
	
	double lastTime = Timer.getFPGATimestamp();
	
	double totalAmpHours;
	
    //IDLE Speed is False, FAST Speed is True
	public String shooterStatus;
	
	public void initShooter() {
        /* first choose the sensor */
        setMotor(shooter1);
        
    	shooter1.reverseOutput(false);
    	shooter1.reverseSensor(false); 
        shooter1.setInverted(false);
        shooter1.changeControlMode(TalonControlMode.Speed);
        
        shooter2.changeControlMode(TalonControlMode.Follower);
        shooter2.set(RobotMap.SHOOTERCAN1);
        
        
	}
    /**
     * This function is called periodically during operator control
     */
    public void startShooter(double speedSP) {
    	shooter1.set(speedSP);
    	
    	double voltage = shooter1.getOutputVoltage();
    	double current = shooter1.getOutputCurrent();
    	
    	double power = voltage * current;
    	
    	double newTime = Timer.getFPGATimestamp();
    	
    	double sampleTime = newTime - lastTime;
    	
    	double currentAmpHours = (sampleTime * current) / (3.6 * (Math.pow(10, 3)));
    	
    	lastTime = newTime;
    	
    	totalAmpHours = totalAmpHours + currentAmpHours;
    	
//    	SmartDashboard.putNumber("Voltage", voltage);
    	SmartDashboard.putNumber("Voltage", shooter1.getOutputVoltage());
    	SmartDashboard.putNumber("Current", shooter1.getOutputCurrent());
    	SmartDashboard.putNumber("Power", power);
    	SmartDashboard.putNumber("Total AMP Hours", totalAmpHours);
    	SmartDashboard.putNumber("Speed", -shooter1.getSpeed());
    	//SmartDashboard.putNumber("Set Point Speed", shooter.getSetpoint());
    	SmartDashboard.putNumber("Set Point Speed", speedSP);
    	SmartDashboard.putNumber("Sample Time", sampleTime);
    	SmartDashboard.putNumber("Closed Loop Error", shooter1.getClosedLoopError());
    	SmartDashboard.putString("Shooter Status", shooterStatus);
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
    	shooterIndexerMotor.set(-1.0);
    	shooterAgitator.set(-1.0);
    }
    
    public void stopShooterIndexer() {
    	shooterIndexerMotor.set(0);
    	shooterAgitator.set(0);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new StopShooterIndexer());
    }
}

