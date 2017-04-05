
package org.usfirst.frc.team1099.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1099.robot.commands.DoNothing;
import org.usfirst.frc.team1099.robot.commands.Baseline;
import org.usfirst.frc.team1099.robot.commands.CenterGearPeg;
import org.usfirst.frc.team1099.robot.commands.LeftGearPeg;
import org.usfirst.frc.team1099.robot.commands.ShooterAuto;
import org.usfirst.frc.team1099.robot.subsystems.Climber;
import org.usfirst.frc.team1099.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1099.robot.subsystems.ShooterSpeedControl;
import org.usfirst.frc.team1099.robot.subsystems.ShooterVBus;
import org.usfirst.frc.team1099.robot.subsystems.BallIntake;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final Drivetrain drivetrain = new Drivetrain();
	public static final BallIntake intake = new BallIntake();
	public static final Climber climber = new Climber();
	//public static final ShooterSpeedControl shooter = new ShooterSpeedControl();
	public static final ShooterVBus shooter = new ShooterVBus();
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		chooser.addDefault("Do Nothing", new DoNothing());
		chooser.addObject("Baseline Drive Forward", new Baseline());
		chooser.addObject("Center Gear Peg Backward", new CenterGearPeg());
		chooser.addObject("Left Gear Peg Backward", new LeftGearPeg());
		chooser.addObject("Shoot! (red)", new ShooterAuto(ShooterAuto.RED));
		chooser.addObject("Shoot! (blue)", new ShooterAuto(ShooterAuto.BLUE));
		
		SmartDashboard.putData("Auto mode", chooser);
		
		UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture(0);
		UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(1);
		
		camera0.setResolution(320, 240);
		camera1.setResolution(320, 240);
		
		camera0.setFPS(10);
		camera1.setFPS(10);
		
		if(Robot.drivetrain.getShiftStatus() != false) {
			Robot.drivetrain.shiftLow();
		}
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	
	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		displayEncoderData();
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public void displayEncoderData() {
		SmartDashboard.putNumber("Left Encoder Position", Robot.drivetrain.getLeftEncoderPosition());
		SmartDashboard.putNumber("Right Encoder Position", Robot.drivetrain.getRightEncoderPosition());		
	}
	
	
	
}
