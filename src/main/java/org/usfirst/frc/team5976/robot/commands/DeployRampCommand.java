package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5976.robot.subsystems.RampSubsystem;

public class DeployRampCommand extends Command {
    private Servo servo;
    private DriverStation driverStation;

    public DeployRampCommand(RampSubsystem rampSubsystem) {
        servo = rampSubsystem.getServo();
        driverStation = DriverStation.getInstance();
    }

    @Override
    protected void execute() {
        if (driverStation.isOperatorControl()) {
            double matchTime = driverStation.getMatchTime();
            System.out.println("Deploy ramp attempted at " + matchTime);
            if (matchTime < 30)
                servo.setPosition(.2);  //  TODO: find actual servo position value
            else if (matchTime < 45)
                servo.setPosition(0.3);
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
