package org.usfirst.frc.team3946.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LaunchGroup extends CommandGroup {
    
    public  LaunchGroup() {
    	addSequential(new LowerIntake(2.0));
    	addSequential(new Launch(1.0));
    	addSequential(new PosCatForLoad(3.0));
        addSequential(new LockLatch(1.0));
        addSequential(new PosCatForLaunch(2.0));
        //time lower intake
    }
}
