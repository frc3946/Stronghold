package org.usfirst.frc.team3946.robot.subsystems;


import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.ArcadeDrive;
import org.usfirst.frc.team3946.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public CANTalon fRight = new CANTalon(RobotMap.fRightDriveTalon);
    public CANTalon fLeft = new CANTalon(RobotMap.fLeftDriveTalon);
    public CANTalon bRight = new CANTalon(RobotMap.bRightDriveTalon);
    public CANTalon bLeft = new CANTalon(RobotMap.bLeftDriveTalon);
    public RobotDrive robotDrive = new RobotDrive(fLeft, bLeft, fRight, bRight);
   
    public void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
        robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
        robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
        LiveWindow.addActuator("Drive Motors", "fRight", fRight);
        LiveWindow.addActuator("Drive Motors", "fLeft", fLeft);
        LiveWindow.addActuator("Drive Motors", "bRight", bRight);
        LiveWindow.addActuator("Drive Motors", "bLeft", bLeft);
    }
      
    public void Drive(double speedLeft, double speedRight){
    	fRight.set(speedRight);
    	fLeft.set(speedLeft);
    	bRight.set(speedRight);
    	bLeft.set(speedLeft);
    }
    
    public void ReverseDrive(){
        robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
        robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
        robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
        robotDrive.setInvertedMotor(MotorType.kRearRight, true);
    }
    
    public void ForwardDrive(){
        robotDrive.setInvertedMotor(MotorType.kFrontLeft, false);
        robotDrive.setInvertedMotor(MotorType.kFrontRight, false);
        robotDrive.setInvertedMotor(MotorType.kRearRight, false);
        robotDrive.setInvertedMotor(MotorType.kRearLeft, false);
    }
}