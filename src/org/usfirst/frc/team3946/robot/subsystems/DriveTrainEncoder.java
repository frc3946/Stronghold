package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainEncoder extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Encoder rightWheelEncoder = new Encoder(RobotMap.rightWheelEncoderA, RobotMap.rightWheelEncoderB, true, EncodingType.k4X);
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    } //end of initDefaultCommand method
    public void initEncoders(){
    	rightWheelEncoder.setDistancePerPulse(1); // feet /ticks
    	rightWheelEncoder.setMinRate(.1);
    	rightWheelEncoder.setSamplesToAverage(7);
    	
    }
    
    public double getDistance()
    {
    	return rightWheelEncoder.getDistance();
    }
    
    public double getRate()
    {
    	return rightWheelEncoder.getRate();
    	
    }
    
} //end of DriveTrainEncoder class

