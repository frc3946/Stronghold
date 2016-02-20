package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LaunchLatch extends Subsystem {
    
	private final DoubleSolenoid latch = new DoubleSolenoid(RobotMap.xLatch, RobotMap.pLatch);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
    
    public void lock() {
    	latch.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void launch() {
    	latch.set(DoubleSolenoid.Value.kForward);
    }
}

