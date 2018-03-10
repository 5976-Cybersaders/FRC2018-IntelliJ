package org.usfirst.frc.team5976.robot.commands.autonomous;

import java.util.concurrent.atomic.AtomicBoolean;

public class AutonomousTasksStatus {
    public static final AtomicBoolean LIFT_IS_RAISED = new AtomicBoolean(false);
    public static final AtomicBoolean RELEASE_IS_COMPLETED = new AtomicBoolean(false);
}
