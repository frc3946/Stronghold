package org.usfirst.frc.team3946.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveTest extends CommandGroup {
    
    public AutoDriveTest() {
    	addSequential(new Drive(5, .5));
    	addSequential(new Drive(0, 0));
    }
}
