package org.usfirst.frc.team1099.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	//Drivetrain Motors:
	public static int CANDRIVE1 = 0;
	public static int CANDRIVE2 = 1;
	public static int CANDRIVE3 = 2;
	public static int CANDRIVE4 = 3;
	public static int CANDRIVE5 = 4;
	public static int CANDRIVE6 = 5;
	
	//Pneumatics:
	public static int SHIFTHIGH = 0;
	public static int SHIFTLOW = 1;
	
	//Joysticks & Gamepads:
	public static int LSTICK = 0;
	public static int RSTICK = 1;
	public static int GAMEPAD = 2;
	
	//Joystick Buttons:
	public static int SHIFTHIGHBUTTON = 3;
	public static int SHIFTLOWBUTTON = 2;
	
}
