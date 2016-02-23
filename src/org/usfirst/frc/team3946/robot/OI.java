package org.usfirst.frc.team3946.robot;

import org.usfirst.frc.team3946.robot.commands.BallPickupForward;
import org.usfirst.frc.team3946.robot.commands.BallPickupReverse;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team3946.robot.commands.AutoAim;
import org.usfirst.frc.team3946.robot.commands.Launch;
import org.usfirst.frc.team3946.robot.commands.LaunchGroup;
import org.usfirst.frc.team3946.robot.commands.LockLatch;
import org.usfirst.frc.team3946.robot.commands.LowerIntake;
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
//LeftBumper - auto aim
//RightBumper - launch
//Start - switch driving direction
//Back - ready for launch commandgroup
    
    public OI() {
    	driveController.setDeadband(0.2);
    	
    //Catapult
    	Button launch = new JoystickButton(driveController, XboxController.RightBumper);
    	Button autoAim = new JoystickButton(driveController, XboxController.LeftBumper);
    	Button readyForLaunch = new JoystickButton(driveController, XboxController.Back);
    	launch.whenPressed(new Launch(1.0));
    	autoAim.whileHeld(new AutoAim());
    	readyForLaunch.whenPressed(new ReadyForLaunch());
    		
    //Intake
    	Button lowerIntake = new JoystickButton(driveController, XboxController.B);
    	Button raiseIntake = new JoystickButton(driveController, XboxController.Y);
    	//Button posCatForLoad = new JoystickButton(driveController, XboxController.B);
    	//Button lockLatch = new JoystickButton(driveController, XboxController.Y);
    	Button ballPickupForward = new JoystickButton(driveController, XboxController.X);
    	Button ballPickupReverse = new JoystickButton(driveController, XboxController.A);
		lowerIntake.whileActive(new LowerIntake(3.0)); //time
		raiseIntake.whileActive(new RaiseIntake());
		//posCatForLoad.whenPressed(new PosCatForLaunch(1.0)); //time
		//lockLatch.whenPressed(new LockLatch(1.0));
		ballPickupForward.whileActive(new BallPickupForward());
		ballPickupReverse.whileActive(new BallPickupReverse());
		
	//Driving
		Button switchDirection = new JoystickButton(driveController, XboxController.Start);
		switchDirection.whenPressed(new SwitchDirection());
    }
}

