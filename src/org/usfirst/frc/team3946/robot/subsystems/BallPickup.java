package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.BallPickupStop;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallPickup extends Subsystem {
	  public Talon ballPickup = new Talon(RobotMap.ballPickupTalon);
	  double ballPickupSpeed = 1.0;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new BallPickupStop());
    }
    public void Reverse() {
    	ballPickup.set(-ballPickupSpeed);
    }
    public void Stop() {
    	ballPickup.set(0);
    }
    public void Forward() {
    	ballPickup.set(ballPickupSpeed);
    }
}

