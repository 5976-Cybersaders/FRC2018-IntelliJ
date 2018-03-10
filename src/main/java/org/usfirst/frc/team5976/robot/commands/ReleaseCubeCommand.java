package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.commands.autonomous.AutonomousTasksStatus;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;

//This is different from grab because it does tell other commands when it finishes in addition to timing out
public class ReleaseCubeCommand extends GrabberCommand {

    public ReleaseCubeCommand(GrabberSubsystem grabberSubsystem, double timeOutSeconds) {
        super(grabberSubsystem, 0, timeOutSeconds);
    }

    @Override
    protected boolean isFinished() {
        boolean done = super.isFinished();
        if (done) {
            AutonomousTasksStatus.RELEASE_IS_COMPLETED.set(true);
        }

        return done;
    }
}
