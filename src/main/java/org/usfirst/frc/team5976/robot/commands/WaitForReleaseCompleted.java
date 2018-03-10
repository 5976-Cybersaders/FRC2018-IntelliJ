package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5976.robot.commands.autonomous.AutonomousTasksStatus;

public class WaitForReleaseCompleted extends Command {

    @Override
    protected boolean isFinished() {
        return AutonomousTasksStatus.RELEASE_IS_COMPLETED.get();
    }
}
