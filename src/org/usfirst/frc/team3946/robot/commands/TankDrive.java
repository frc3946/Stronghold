package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TankDrive extends Command {

    public TankDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftStick = Robot.oi.leftController.getLeftStickY();
    	double rightStick = Robot.oi.rightController.getLeftStickY();
    	Robot.drivetrain.Drive(leftStick, -rightStick);
    	
    	SmartDashboard.putNumber("Actual Right Speed", Robot.driveTrainEncoder.getRightRate());
    	SmartDashboard.putNumber("Actual Right Distance", Robot.driveTrainEncoder.getRightDistance());
    	
    	SmartDashboard.putNumber("Actual Left Speed", Robot.driveTrainEncoder.getLeftRate());
    	SmartDashboard.putNumber("Actual Left Distance", Robot.driveTrainEncoder.getLeftDistance());

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