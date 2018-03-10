package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;

//This is different from release because it doesn't tell other commands when it finishes. It just times out
public class GrabCubeCommand extends GrabberCommand {

    public GrabCubeCommand(GrabberSubsystem grabberSubsystem, double timeOutSeconds) {
        super(grabberSubsystem, 1, timeOutSeconds);
    }

}
