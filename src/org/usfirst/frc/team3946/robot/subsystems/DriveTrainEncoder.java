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
    Encoder leftWheelEncoder = new Encoder(RobotMap.leftWheelEncoderA, RobotMap.leftWheelEncoderB, true, EncodingType.k4X);
    
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    } //end of initDefaultCommand method
    public void initEncoders(){
    	//6'11"=83 inches per 3430 ticks
    	rightWheelEncoder.setDistancePerPulse(-83.0/3430.0); // inches /ticks
    	rightWheelEncoder.setMinRate(.1);
    	rightWheelEncoder.setSamplesToAverage(7);
    	leftWheelEncoder.setDistancePerPulse(83.0/3430.0); // inches /ticks
    	leftWheelEncoder.setMinRate(.1);
    	leftWheelEncoder.setSamplesToAverage(7);
    }
    
    public double getRightDistance()
    {
    	return rightWheelEncoder.getDistance();
    }
    
    public double getRightRate()
    {
    	return rightWheelEncoder.getRate();
    	
    }
    public double getLeftDistance()
    {
    	return leftWheelEncoder.getDistance();
    }
    
    public double getLeftRate()
    {
    	return leftWheelEncoder.getRate();
    	
    }
    
    public double getAverageDistance(){
    	return (getLeftDistance() + getRightDistance()) / 2;
    }
    
    public void resetEncoders(){
    	leftWheelEncoder.reset();
    	rightWheelEncoder.reset();
    }
    
} //end of DriveTrainEncoder class

