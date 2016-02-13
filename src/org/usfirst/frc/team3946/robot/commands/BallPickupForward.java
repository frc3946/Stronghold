package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BallPickupForward extends Command {

	Timer timer = new Timer();
	public double[] ballFinderRanges = new double[25];
	int ballFinderIndex = 0;
	boolean ballFinderArrayFull =false;
	boolean timeStart = false;
	
    public BallPickupForward() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ballPickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ballPickup.Forward();
    	timer.reset();
    	ballFinderIndex = 0;
    	ballFinderArrayFull =false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double ballFinderVoltage = Robot.ballFinder.getVoltage();
    	
    	ballFinderRanges[ballFinderIndex] = ballFinderVoltage;
    	ballFinderIndex++;
    	if (ballFinderIndex == 25)
    		ballFinderArrayFull = true;
    	ballFinderIndex %= 25;
    	if (ballFinderArrayFull == false)
    		return;
    	
    	double ballFinderAverage = 0.0;
    	for (double i : ballFinderRanges)
    		ballFinderAverage += i;
    	
    	ballFinderAverage /= 25;
    	SmartDashboard.putNumber("ballFinderAverage", ballFinderAverage);
    	if (ballFinderAverage > 1.0 && !timeStart) {
    		//start timer :) :) :)
    		timer.start();
    		timeStart = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//No ball 0.6	//Away 1.2	//closest 1.8
    	SmartDashboard.putNumber("timer", timer.get() );
    	if (timer.get() > .5) {
   
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
