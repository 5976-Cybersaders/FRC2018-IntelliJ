package org.usfirst.frc.team5976.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5976.robot.RobotMap;

public class RampSubsystem extends Subsystem {

    private Servo servo;

    public RampSubsystem() {
        servo = new Servo(RobotMap.RAMP_SERVO_ID);

    }

    @Override
    protected void initDefaultCommand() {
    }

    public Servo getServo() {
        return servo;
    }
}
