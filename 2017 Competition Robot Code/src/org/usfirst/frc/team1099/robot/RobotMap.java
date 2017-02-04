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
	public static int LCANDRIVE = 0;
	public static int LCANSLAVE1 = 1;
	public static int LCANSLAVE2 = 2;
	public static int RCANDRIVE = 3;
	public static int RCANSLAVE1 = 4;
	public static int RCANSLAVE2 = 5;
	
	//Component Motors:
	public static int INTAKEMOTOR = 6;
	public static int CLIMBERMOTOR = 7;
	public static int SHOOTERMOTOR = 8;
	
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
	
	//Gamepad Buttons:
	public static int INTAKEIN = 5;
	public static int INTAKEOUT = 6;
	
	//Gamepad Axes:
	public static int CLIMBERAXIS = 5;
	
}
