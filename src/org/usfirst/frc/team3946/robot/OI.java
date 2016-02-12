package org.usfirst.frc.team3946.robot;

import libraries.XboxController;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public XboxController driveController = new XboxController(0);
    
    public OI() {
    	driveController.setDeadband(0.2);
    }
}

