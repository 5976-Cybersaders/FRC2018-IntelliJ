package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5976.robot.commands.autonomous.AutonomousTasksStatus;

public class WaitForLiftRaised extends Command {

    @Override
    protected boolean isFinished() {
        return AutonomousTasksStatus.LIFT_IS_RAISED.get();
    }
}
