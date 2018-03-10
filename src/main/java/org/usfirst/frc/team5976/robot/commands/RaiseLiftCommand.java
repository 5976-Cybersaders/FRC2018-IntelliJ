package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.autonomous.AutonomousTasksStatus;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class RaiseLiftCommand extends MoveLiftCommand {

    private RaiseLiftCommand(LiftSubsystem liftSubsystem, int setPoint) {
        super(liftSubsystem, setPoint);
    }

    public static RaiseLiftCommand RaiseLiftToSwitch(LiftSubsystem liftSubsystem) {
        return new RaiseLiftCommand(liftSubsystem, 7500);
    }

    public static RaiseLiftCommand RaiseLiftToScale(LiftSubsystem liftSubsystem) {
        return new RaiseLiftCommand(liftSubsystem, 17000);
    }

    private boolean isAtSetPoint() {
        return counter > 50 && Math.abs(talon.getSelectedSensorPosition(0) - setPoint) < SmartDashboardMap.LIFT_ALLOWABLE_ERROR.getDouble();
    }

    @Override
    protected void execute() {
        super.execute();
        //TODO: Remove counter > 200 check because it's only for the test robot
        if (isAtSetPoint() || counter > 200) {
            AutonomousTasksStatus.LIFT_IS_RAISED.set(true);
        }
    }

    @Override
    protected boolean isFinished() {
        return AutonomousTasksStatus.RELEASE_IS_COMPLETED.get();
    }
}
