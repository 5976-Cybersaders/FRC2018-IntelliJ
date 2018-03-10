package org.usfirst.frc.team5976.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    //DriveTrain Motors CAN IDs
    //Master motors are the front motor
    public static final int LEFT_MASTER_TALON_ID = 2;
    public static final int LEFT_SLAVE_TALON_ID = 1;
    public static final int RIGHT_MASTER_TALON_ID = 4;
    public static final int RIGHT_SLAVE_TALON_ID = 3;

    //Ramp subsystem
    public static final int RAMP_SERVO_ID = 1;

    //Lift subsystem
    public static final int LIFT_TALON_ID = 5;

    //Grabber subsystem
    public static final int GRABBER_SERVO = 9;


    public static final double TURN_DIAMETER = 23.5; //23.5 || 49
}