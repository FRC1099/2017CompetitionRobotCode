package org.usfirst.frc.team1099.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team1099.robot.commands.Drive.ShiftHigh;
import org.usfirst.frc.team1099.robot.commands.Drive.ShiftLow;
import org.usfirst.frc.team1099.robot.commands.Intake.IntakeIn;
import org.usfirst.frc.team1099.robot.commands.Shooter.StartShooterFast;
import org.usfirst.frc.team1099.robot.commands.Shooter.StartShooterIdle;
import org.usfirst.frc.team1099.robot.commands.Shooter.StartShooterIndexer;
import org.usfirst.frc.team1099.robot.commands.Shooter.StopShooter;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	public static Joystick lstick = new Joystick(RobotMap.LSTICK);
	public static Joystick rstick = new Joystick(RobotMap.RSTICK);
	public static Joystick gamepad = new Joystick(RobotMap.GAMEPAD);
	
	public OI() {
		JoystickButton shiftHigh = new JoystickButton(rstick, RobotMap.SHIFTHIGHBUTTON);
		shiftHigh.whenPressed(new ShiftHigh());
			
		JoystickButton shiftLow = new JoystickButton(lstick, RobotMap.SHIFTLOWBUTTON);
		shiftLow.whenPressed(new ShiftLow());
		
		JoystickButton intakeIn = new JoystickButton(gamepad, RobotMap.INTAKEIN);
		intakeIn.whileHeld(new IntakeIn());
		
		JoystickButton indexerButton = new JoystickButton(gamepad, RobotMap.INDEXERBUTTON);
		indexerButton.whileHeld(new StartShooterIndexer());
		
		JoystickButton shooterFast = new JoystickButton(gamepad, RobotMap.SHOOTERFAST);
		shooterFast.whenPressed(new StartShooterFast());
		
		JoystickButton shooterIdle = new JoystickButton(gamepad, RobotMap.SHOOTERIDLE);
		shooterIdle.whenPressed(new StartShooterIdle());
		
		JoystickButton stopShooter = new JoystickButton(gamepad, RobotMap.SHOOTERSTOP);
		stopShooter.whenPressed(new StopShooter());
	}
	
	public double getRightAxis() {
		return gamepad.getRawAxis(RobotMap.CLIMBERAXIS);
	}
}
