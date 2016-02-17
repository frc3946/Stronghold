package org.usfirst.frc.team3946.robot;

import org.usfirst.frc.team3946.robot.commands.BallPickupForward;
import org.usfirst.frc.team3946.robot.commands.BallPickupReverse;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team3946.robot.commands.AutoAim;
import org.usfirst.frc.team3946.robot.commands.Launch;
import org.usfirst.frc.team3946.robot.commands.LaunchGroup;
import org.usfirst.frc.team3946.robot.commands.LowerIntake;
import org.usfirst.frc.team3946.robot.commands.PosCatForLaunch;
import org.usfirst.frc.team3946.robot.commands.PosCatForLoad;
import org.usfirst.frc.team3946.robot.commands.RaiseIntake;
import org.usfirst.frc.team3946.robot.commands.ReadyForLaunch;

import libraries.XboxController;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public XboxController driveController = new XboxController(0);
	
//	A - position catapult for launch
//	B - position catapult for loading
//	X - lower intake device
//	Y - raise intake device
//	LeftBumper
//	RightBumper - launch ball
//	LeftTrigger - runs intake wheels out
//	RightTrigger - runs intake wheels in
    
    public OI() {
    	driveController.setDeadband(0.2);
    	
    //Catapult
    	Button launch = new JoystickButton(driveController, XboxController.RightBumper);
    	Button autoAim = new JoystickButton(driveController, XboxController.LeftBumper);
    	//Button posCatForLaunch = new JoystickButton(driveController, XboxController.B);
    	Button readyForLaunch = new JoystickButton(driveController, XboxController.Back);
    	launch.whenPressed(new LaunchGroup());
    	autoAim.whileHeld(new AutoAim());
    	readyForLaunch.whenPressed(new ReadyForLaunch());
    	//posCatForLoad.whileActive(new PosCatForLoad());
    		
    //Intake
    	Button lowerIntake = new JoystickButton(driveController, XboxController.B);
    	Button raiseIntake = new JoystickButton(driveController, XboxController.Y);
    	Button ballPickupForward = new JoystickButton(driveController, XboxController.X);
    	Button ballPickupReverse = new JoystickButton(driveController, XboxController.Start);
		lowerIntake.whileActive(new LowerIntake());
		raiseIntake.whileActive(new RaiseIntake());
		ballPickupForward.whileActive(new BallPickupForward());
		ballPickupReverse.whileActive(new BallPickupReverse());
    }
}

