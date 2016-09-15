package org.usfirst.frc.team3946.robot;


import org.usfirst.frc.team3946.robot.commands.BallPickupForward;
import org.usfirst.frc.team3946.robot.commands.BallPickupReverse;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

import org.usfirst.frc.team3946.robot.commands.AutoAim;
import org.usfirst.frc.team3946.robot.commands.Launch;
import org.usfirst.frc.team3946.robot.commands.LaunchGroup;
import org.usfirst.frc.team3946.robot.commands.LockLatch;
import org.usfirst.frc.team3946.robot.commands.LowerIntake;
import org.usfirst.frc.team3946.robot.commands.PosCatForLaunch;
import org.usfirst.frc.team3946.robot.commands.PosCatForLoad;
import org.usfirst.frc.team3946.robot.commands.RaiseIntake;
import org.usfirst.frc.team3946.robot.commands.SwitchDirection;

import libraries.XboxController;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public XboxController leftController = new XboxController(0);
	public XboxController rightController = new XboxController(1);

//UPDATE LIST
//RightTrigger - ball pickup forward
//LeftTrigger - ball pickup reverse
//Y - raise intake
//B - lower intake
//A - position catapult to launch
//X - position catapult to load
//LeftBumper - lock latch
//RightBumper - launch
//Start - auto aim
//Back - launch group
    
    public OI() {
    	leftController.setDeadband(0.2);
    	rightController.setDeadband(0.2);
    	
    //Catapult
    	Button launch = new JoystickButton(leftController, XboxController.RightBumper);
    	//Button autoAim = new JoystickButton(driveController, XboxController.Start);
    	Button lockLatch = new JoystickButton(leftController, XboxController.LeftBumper);
    	Button LaunchGroup = new JoystickButton(leftController, XboxController.Back);
    	launch.whenPressed(new Launch());
    	lockLatch.whenPressed(new LockLatch());
    	//autoAim.whenPressed(new AutoAim());
    	LaunchGroup.whenPressed(new LaunchGroup());

    //Intake
    	Button lowerIntake = new JoystickButton(leftController, XboxController.X);
    	Button raiseIntake = new JoystickButton(leftController, XboxController.Y);
    	Button posCatForLoad = new JoystickButton(leftController, XboxController.B);
    	Button posCatForLaunch = new JoystickButton(leftController, XboxController.A);
		lowerIntake.whenPressed(new LowerIntake());
		raiseIntake.whenPressed(new RaiseIntake());
		posCatForLoad.whenPressed(new PosCatForLoad());
		posCatForLaunch.whenPressed(new PosCatForLaunch()) ;
		
//	//Driving
//		Button switchDirection = new JoystickButton(driveController, XboxController.Start);
//		switchDirection.whenPressed(new SwitchDirection());
    }
}

