package org.usfirst.frc.team3946.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// PWM
	public static int ballPickupTalon = 1;

	// CAN
	public static int fRightDriveTalon = 1;
	public static int fLeftDriveTalon = 2;
	public static int bRightDriveTalon = 3;
	public static int bLeftDriveTalon = 4;

	// DIO
	public static int rightWheelEncoderA = 0;
	public static int rightWheelEncoderB = 1;
	public static int leftWheelEncoderA = 8; // moved to 8
	public static int leftWheelEncoderB = 9; // moved to 9

	// Relay

	// Analog In
	public static int gyro = 1;

	// Solenoids
	public static int xIntake = 0;
	public static int pIntake = 1;
	public static int xLatch = 2;
	public static int pLatch = 3;
	public static int xCatapult = 4;
	public static int pCatapult = 5;

}
