package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class ReturnLiftToStartPointCommand extends MoveLiftCommand {

    public ReturnLiftToStartPointCommand(LiftSubsystem liftSubsystem) {
        super(liftSubsystem, 0);
    }

    @Override
    protected boolean isFinished() {
        return DriverStation.getInstance().isOperatorControl();
    }
}
