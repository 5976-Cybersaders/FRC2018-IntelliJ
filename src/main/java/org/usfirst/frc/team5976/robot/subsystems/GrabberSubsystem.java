package org.usfirst.frc.team5976.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5976.robot.RobotMap;

public class GrabberSubsystem extends Subsystem {
    private Servo servo, servo2;

    public GrabberSubsystem() {
        servo = new Servo(RobotMap.GRABBER_SERVO);
        servo2 = new Servo(8);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public Servo getServo() {
        return servo;
    }

    public Servo getServo2() {
        return servo2;
    }
}
