package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.StopIntakeMovement;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class IntakePositioner extends Subsystem {
    
	private final DoubleSolenoid intake = new DoubleSolenoid(RobotMap.xIntake, RobotMap.pIntake);
    
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new StopIntakeMovement());
        LiveWindow.addActuator("Pistons", "Intake", intake);
    }
    
    public void raiseIntake() {
    	intake.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void lowerIntake() {
    	intake.set(DoubleSolenoid.Value.kForward);
    }
    
    public void stopIntake() {
    	intake.set(DoubleSolenoid.Value.kOff);
    }
}