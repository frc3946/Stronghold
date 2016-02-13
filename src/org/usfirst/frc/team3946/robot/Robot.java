
package org.usfirst.frc.team3946.robot;

import org.usfirst.frc.team3946.robot.commands.AutoDriveTest;
import org.usfirst.frc.team3946.robot.subsystems.BallPickup;
import org.usfirst.frc.team3946.robot.subsystems.CatapultPositioner;
import org.usfirst.frc.team3946.robot.subsystems.DriveTrainEncoder;
import org.usfirst.frc.team3946.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3946.robot.subsystems.IntakePositioner;
import org.usfirst.frc.team3946.robot.subsystems.LaunchLatch;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
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
    Command autonomousCommand;
    SendableChooser chooser;


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		driveTrainEncoder.initEncoders();
        chooser = new SendableChooser();

        SmartDashboard.putData("Auto mode", chooser);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (AutoDriveTest) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
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
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    	SmartDashboard.putNumber("Actual Right Speed", Robot.driveTrainEncoder.getRightRate());
    	SmartDashboard.putNumber("Actual Right Distance", Robot.driveTrainEncoder.getRightDistance());
    	SmartDashboard.putNumber("Range Finder", Robot.ballFinder.getVoltage());
    	SmartDashboard.putNumber("Gyro", Robot.gyro.getAngle());
    	SmartDashboard.putNumber("Actual Left Speed", Robot.driveTrainEncoder.getLeftRate());
    	SmartDashboard.putNumber("Actual Left Distance", Robot.driveTrainEncoder.getLeftDistance());
    	SmartDashboard.putNumber("Accel X Value", Robot.accel.getX());
    	SmartDashboard.putNumber("Accel Y Value", Robot.accel.getY());
    	SmartDashboard.putNumber("Accel Z Value", Robot.accel.getZ());
    	SmartDashboard.putNumber("Angle", (Math.atan2(Robot.accel.getY(), Robot.accel.getZ())) * (180 / Math.PI));
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
