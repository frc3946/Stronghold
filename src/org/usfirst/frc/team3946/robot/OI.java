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
import org.usfirst.frc.team3946.robot.commands.ReadyForLaunch;
import org.usfirst.frc.team3946.robot.commands.SwitchDirection;

import libraries.XboxController;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public XboxController driveController = new XboxController(0);
	
//X - ball pickup forward
//Y - raise intake
//A - ball pickup reverse
//B - lower intake
//LeftBumper - lock latch
//RightBumper - launch
//Start - auto aim
//Back - launch group
    
    public OI() {
    	driveController.setDeadband(0.2);
    	
    //Catapult
    	Button launch = new JoystickButton(driveController, XboxController.RightBumper);
    	Button autoAim = new JoystickButton(driveController, XboxController.Start);
    	Button lockLatch = new JoystickButton(driveController, XboxController.LeftBumper);
    	Button LaunchGroup = new JoystickButton(driveController, XboxController.Back);
    	launch.whenPressed(new Launch());
    	lockLatch.whenPressed(new LockLatch());
    	autoAim.whenPressed(new AutoAim());
    	LaunchGroup.whenPressed(new LaunchGroup());

    //Intake
    	Button lowerIntake = new JoystickButton(driveController, XboxController.B);
    	Button raiseIntake = new JoystickButton(driveController, XboxController.Y);
    	Button posCatForLoad = new JoystickButton(driveController, XboxController.X);
    	Button posCatForLaunch = new JoystickButton(driveController, XboxController.A);
    	Trigger ballPickupForward = new JoystickButton(driveController, XboxController.RightTrigger);
    	Trigger ballPickupReverse = new JoystickButton(driveController, XboxController.LeftTrigger);
		lowerIntake.whenPressed(new LowerIntake());
		raiseIntake.whenPressed(new RaiseIntake());
		posCatForLoad.whenPressed(new PosCatForLoad());
		posCatForLaunch.whenPressed(new PosCatForLaunch());
		ballPickupForward.whileActive(new BallPickupForward());
		ballPickupReverse.whileActive(new BallPickupReverse());
		
	//Driving
		Button switchDirection = new JoystickButton(driveController, XboxController.Start);
		switchDirection.whenPressed(new SwitchDirection());
    }
}

