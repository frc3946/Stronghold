package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CatapultPositioner extends Subsystem {
    
	private final DoubleSolenoid catapult = new DoubleSolenoid(RobotMap.xCatapult, RobotMap.pCatapult);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void launchReady() {
    	catapult.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void loadReady() {
    	catapult.set(DoubleSolenoid.Value.kForward);   	
    }   
    
    
}

