package org.usfirst.frc.team3946.robot;

import org.usfirst.frc.team3946.robot.commands.BallPickupForward;
import org.usfirst.frc.team3946.robot.commands.BallPickupReverse;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team3946.robot.commands.Launch;
import org.usfirst.frc.team3946.robot.commands.LowerIntake;
import org.usfirst.frc.team3946.robot.commands.PosCatForLaunch;
import org.usfirst.frc.team3946.robot.commands.PosCatForLoad;
import org.usfirst.frc.team3946.robot.commands.RaiseIntake;

import libraries.XboxController;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public XboxController driveController = new XboxController(0);
	
//	A - position catapult for launch
//	B - position catapult for loading
//	X - lwoer intake device
//	Y - raise intake device
//	LeftBumper
//	RightBumper - launch ball
//	LeftTrigger - runs intake wheels out
//	RightTrigger - runs intake wheels in
    
    public OI() {
    	driveController.setDeadband(0.2);
    	
    //Catapult
    	Button launch = new JoystickButton(driveController, XboxController.RightBumper);
    	Button posCatForLaunch = new JoystickButton(driveController, XboxController.B);
    	Button posCatForLoad = new JoystickButton(driveController, XboxController.A);
    	launch.whileActive(new Launch());
    	posCatForLaunch.whileActive(new PosCatForLaunch());
    	posCatForLoad.whileActive(new PosCatForLoad());
    		
    //Intake
    	Button lowerIntake = new JoystickButton(driveController, XboxController.Start);
    	Button raiseIntake = new JoystickButton(driveController, XboxController.Back);
    	Button ballPickupForward = new JoystickButton(driveController, XboxController.X);
    	Button ballPickupReverse = new JoystickButton(driveController, XboxController.Y);
		lowerIntake.whileActive(new LowerIntake());
		raiseIntake.whileActive(new RaiseIntake());
		ballPickupForward.whileActive(new BallPickupForward());
		ballPickupReverse.whileActive(new BallPickupReverse());
    }
}

