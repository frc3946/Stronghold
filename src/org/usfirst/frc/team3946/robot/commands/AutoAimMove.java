package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.Robot;
import org.usfirst.frc.team3946.robot.ThreadedPi;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoAimMove extends Command {

	double totalDistance;
	double Kp = .03;
	double distanceToTravel;
	double currentDistance;

	public AutoAimMove() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(3);
		Robot.gyro.reset();
		Robot.driveTrainEncoder.resetEncoders();
		totalDistance = ThreadedPi.getDistance();
		currentDistance = Robot.driveTrainEncoder.getAverageDistance();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double angle = Robot.gyro.getAngle();
		distanceToTravel = totalDistance - currentDistance;
		if (distanceToTravel >= 1) {
			Robot.drivetrain.robotDrive.arcadeDrive(.6, -angle * Kp);
		}

		if (distanceToTravel <= -1) {
			Robot.drivetrain.robotDrive.arcadeDrive(-.6, (angle + 180) * Kp);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Math.abs(distanceToTravel) < 3) {
			return true;
		} else {
			return isTimedOut();
		}
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
