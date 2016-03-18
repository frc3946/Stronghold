package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ControlIntakeMotor extends Command {

    public ControlIntakeMotor() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ballPickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftTrigger = Robot.oi.driveController.getLeftTrigger();
    	double rightTrigger = Robot.oi.driveController.getRightTrigger();
    	if (Math.abs(leftTrigger) > .1) {
    		Robot.ballPickup.Reverse();
    	} else if (Math.abs(rightTrigger) > .1) {
    		Robot.ballPickup.Forward();
    	} else
    		Robot.ballPickup.Stop();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
