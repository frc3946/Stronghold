package org.usfirst.frc.team3946.robot;

import org.usfirst.frc.team3946.robot.commands.Launch;
import org.usfirst.frc.team3946.robot.commands.LowerIntake;
import org.usfirst.frc.team3946.robot.commands.PosCatForLaunch;
import org.usfirst.frc.team3946.robot.commands.PosCatForLoad;
import org.usfirst.frc.team3946.robot.commands.RaiseIntake;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import libraries.XboxController;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public XboxController driveController = new XboxController(0);
    
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
    	Button lowerIntake = new JoystickButton(driveController, XboxController.X);
    	Button raiseIntake = new JoystickButton(driveController, XboxController.Y);
    	Button ballPickupForward = new JoystickButton(driveController, XboxController.RightTrigger);
    	Button ballPickupReverse = new JoystickButton(driveController, XboxController.LeftTrigger);
			lowerIntake.whileActive(new LowerIntake());
			raiseIntake.whileActive(new RaiseIntake());
			ballPickupForward.whileActive(new BallPickupForward());
			ballPickupReverse.whileActive(new BallPickupReverse());
    }
}

