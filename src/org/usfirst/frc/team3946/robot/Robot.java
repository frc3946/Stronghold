package org.usfirst.frc.team3946.robot;

import org.usfirst.frc.team3946.robot.commands.ArcadeDrive;
import org.usfirst.frc.team3946.robot.commands.AutoTravel;
import org.usfirst.frc.team3946.robot.commands.LoadPrefNames;
import org.usfirst.frc.team3946.robot.commands.TankDrive;
import org.usfirst.frc.team3946.robot.subsystems.BallPickup;
import org.usfirst.frc.team3946.robot.subsystems.CatapultPositioner;
import org.usfirst.frc.team3946.robot.subsystems.DriveTrainEncoder;
import org.usfirst.frc.team3946.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3946.robot.subsystems.IntakePositioner;
import org.usfirst.frc.team3946.robot.subsystems.LaunchLatch;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drivetrain drivetrain = new Drivetrain();
	public static DriveTrainEncoder driveTrainEncoder = new DriveTrainEncoder();
	public static AnalogGyro gyro = new AnalogGyro(1);
	public static AnalogInput ballFinder = new AnalogInput(3);
	public static BallPickup ballPickup = new BallPickup();
	public static IntakePositioner intakePositioner = new IntakePositioner();
	public static LaunchLatch launchLatch = new LaunchLatch();
	public static CatapultPositioner catapultPositioner = new CatapultPositioner();
	public static Compressor compressor = new Compressor(0);
	public static Accelerometer accel = new BuiltInAccelerometer();
	public static ThreadedPi threadedpi = new ThreadedPi();
	//autonomous chooser
	Command autonomousCommand;
	SendableChooser chooser;
	//camera chooser
	public static SendableChooser cameraSelector;
	static String lastSelected = "";
	static int currSession;
	static int sessionfront;
	static int sessionback;
	Image frame;
	//drivetrain chooser
	public static SendableChooser drivetrainSelector;
	static String lastDTSelected = "";
	Command driveType;
	int currDriver;
	//preferences
	public static Preferences prefs;
	public static double distanceTarget = 130;
	public static double distanceOffset = 0;
	public static double angleMultiplier = 1;
	public static double angleAddition = 0;
	public static double distanceMultiplier = 1;
	public static double distanceAddition = 0;
	public static double leftInches = 0;
	public static double rightInches = 0;
	public static double leftTicks = 0;
	public static double rightTicks = 0;


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		driveTrainEncoder.initEncoders();
		//autonomous chooser
		chooser = new SendableChooser();
		chooser.addDefault("Position One", "Position One");
		chooser.addObject("Position Two", "Position Two");
		chooser.addObject("Position Three", "Position Three");
		chooser.addObject("Position Four", "Position Four");
		chooser.addObject("Position Five", "Position Five");
		chooser.addObject("Do Nothing", "Do Nothing");
		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putData("LoadPrefNames", new LoadPrefNames());
		//camera chooser
		cameraSelector = new SendableChooser();
		cameraSelector.addDefault("Front View", "Front View");
		cameraSelector.addObject("Back View", "Back View");
		cameraSelector.addObject("Manual Change", "Manual Change");
		SmartDashboard.putData("Camera Selector", cameraSelector);
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		sessionfront = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		sessionback = NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		currSession = sessionback;
		NIVision.IMAQdxConfigureGrab(currSession);
		//drivetrain chooser
		drivetrainSelector = new SendableChooser();
		drivetrainSelector.addDefault("Tank Drive", "Tank Drive");
		drivetrainSelector.addObject("Arcade Drive", "Arcade Drive");
		SmartDashboard.putData("Drivetrain Selector", drivetrainSelector);
		//preferences
		prefs = Preferences.getInstance();
		distanceTarget = prefs.getDouble("DistanceTarget", distanceTarget);
		distanceOffset =  prefs.getDouble("DistanceOffset", distanceOffset);
		angleMultiplier = prefs.getDouble("AngleMultipler", angleMultiplier);
		angleAddition = prefs.getDouble("AngleAddition", angleAddition);
		distanceMultiplier = prefs.getDouble("DistanceMultipler", distanceMultiplier);
		distanceAddition = prefs.getDouble("DistanceAddition", distanceAddition);
		leftInches = prefs.getDouble("lWheelInches", leftInches);
		rightInches = prefs.getDouble("fRightInches", rightInches);
		leftTicks = prefs.getDouble("lWheelTicks", leftTicks);
		rightTicks = prefs.getDouble("rWheelTicks", rightTicks);
		}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		

	}

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
	public void autonomousInit() {
		String autoSelected = (String) chooser.getSelected();
		switch (autoSelected) {
		case "Position One":
		default:
			autonomousCommand = new AutoTravel(5, 60);
			break;
		case "Position Two":
			autonomousCommand = new AutoTravel(5, 30);
			break;
		case "Position Three":
				autonomousCommand = new AutoTravel(5, 15);
				break;
		case "Position Four":
				autonomousCommand = new AutoTravel(5, 5);
				break;
		case "Position Five":
				autonomousCommand = new AutoTravel(5, -30);
				break;
		case "Do Nothing":
			autonomousCommand = new AutoTravel(0, 0);
			break;
		}
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Actual Right Speed",
				Robot.driveTrainEncoder.getRightRate());
		SmartDashboard.putNumber("Actual Right Distance",
				Robot.driveTrainEncoder.getRightDistance());
		SmartDashboard.putNumber("Range Finder", Robot.ballFinder.getVoltage());
		SmartDashboard.putNumber("Gyro", Robot.gyro.getAngle());
		SmartDashboard.putNumber("Actual Left Speed",
				Robot.driveTrainEncoder.getLeftRate());
		SmartDashboard.putNumber("Actual Left Distance",
				Robot.driveTrainEncoder.getLeftDistance());
		SmartDashboard.putNumber("Accel X Value", Robot.accel.getX());
		SmartDashboard.putNumber("Accel Y Value", Robot.accel.getY());
		SmartDashboard.putNumber("Accel Z Value", Robot.accel.getZ());
		SmartDashboard.putNumber("Angle",
				(Math.atan2(Robot.accel.getY(), Robot.accel.getZ())) * (180 / Math.PI));
		updateCamera();
	}
	
	public void updateCamera(){
		
		
		String cameraSelected = (String) cameraSelector.getSelected();
		NIVision.IMAQdxGrab(currSession, frame, 1);
		CameraServer.getInstance().setImage(frame);
		
		if(cameraSelected == lastSelected){
			return;
		}
		switch (cameraSelected) {
		case "Front View":
     		  NIVision.IMAQdxStopAcquisition(currSession);
     		  currSession = sessionfront;
	          NIVision.IMAQdxConfigureGrab(currSession);
	          Robot.drivetrain.ForwardDrive();
			lastSelected = "Front View";
			break;
		default:
		case "Back View":
    		  NIVision.IMAQdxStopAcquisition(currSession);
       		  currSession = sessionback;
       		  NIVision.IMAQdxConfigureGrab(currSession);
       		  Robot.drivetrain.ReverseDrive();
			lastSelected = "Back View";
			break;
		case "Manual Change":
			break;
		}
	}
	
	public static void switchDirection(){
		
		switch (lastSelected) {
		case "Front View":
     		  NIVision.IMAQdxStopAcquisition(currSession);
     		  currSession = sessionback;
	          NIVision.IMAQdxConfigureGrab(currSession);
	          Robot.drivetrain.ReverseDrive();
			lastSelected = "Back View";
			break;
		default:
		case "Back View":
    		  NIVision.IMAQdxStopAcquisition(currSession);
       		  currSession = sessionfront;
       		  NIVision.IMAQdxConfigureGrab(currSession);
       		  Robot.drivetrain.ForwardDrive();
			lastSelected = "Front View";
			break;
		case "Manual Change":
			break;
		}
	}
	
	public void drivetrainChooser(){
		String drivetrainSelected = (String) drivetrainSelector.getSelected();
		if(drivetrainSelected == lastDTSelected){
			return;
		}
		switch (drivetrainSelected) {
		default:
		case "Tank Drive":
			driveType = new TankDrive();
			driveType.start();
			lastSelected = "Tank Drive";
			break;
		case "Arcade Drive":
			driveType = new ArcadeDrive();
			driveType.start();
			lastSelected = "ArcadeDrive";
			break;
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
