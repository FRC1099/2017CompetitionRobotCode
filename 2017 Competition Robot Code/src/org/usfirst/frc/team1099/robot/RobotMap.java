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
	
	//CAN Motors:
	public static int RCANDRIVE = 0;
	public static int RCANSLAVE1 = 1;
	public static int RCANSLAVE2 = 2;
	public static int LCANDRIVE = 3;
	public static int LCANSLAVE1 = 4;
	public static int LCANSLAVE2 = 5;
	public static int SHOOTERCAN1 = 6;
	public static int SHOOTERCAN2 = 7;
	public static int SHOOTERAGITATORCAN = 8;
	
	//Component Motors:
	public static int INTAKEMOTOR = 0;
	public static int SHOOTERINDEXMOTOR = 1;
	public static int CLIMBERMOTOR = 2;
	;
	
	//Pneumatics:
	public static int SHIFTHIGH = 0;
	public static int SHIFTLOW = 1;
	
	//Joysticks & Gamepads:
	public static int LSTICK = 0;
	public static int RSTICK = 1;
	public static int GAMEPAD = 2;
	
	//Joystick Buttons:
	public static int SHIFTHIGHBUTTON = 1;
	public static int SHIFTLOWBUTTON = 1;
	public static int SLOWDRIVEBUTTON = 3;
	
	//Gamepad Buttons:
	public static int INTAKEIN = 6;
	public static int SHOOTERFAST = 1;
	public static int SHOOTERIDLE = 2;
	public static int SHOOTERSTOP = 4;
	public static int INDEXERBUTTON = 3;
	public static int CLIMBERDOWNVERIFY = 8;

	
	//Gamepad Axes:
	public static int CLIMBERAXIS = 5;
	
}
