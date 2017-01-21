package org.usfirst.frc.team3946.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team3946.robot.Robot.drivetrain;

/**
 *
 */
public class Drive extends Command {

	double s;

	public Drive(double timeout, double speed) {
		// Use requires() here to declare subsystem dependencies
		requires(drivetrain);
		setTimeout(timeout);
		s = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		drivetrain.Drive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
