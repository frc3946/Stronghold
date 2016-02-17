package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoAimMove extends Command {
	
	double feet;
	double Kp = .03;

    public AutoAimMove(double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
		feet = Robot.threadedpi.getDistance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyro.reset();
    	Robot.driveTrainEncoder.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		double angle = Robot.gyro.getAngle();
		double tilt = Math.atan2(Robot.accel.getY(), Robot.accel.getZ())
				* (180 / Math.PI);

		if (Math.abs(tilt) > 20) {
			angle = 0;
		}

		Robot.drivetrain.robotDrive.arcadeDrive(.6, -angle * Kp);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		if (Robot.driveTrainEncoder.getAverageDistance() >= feet) {
			return true;
		} else {
			return false;
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
