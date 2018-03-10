package org.usfirst.frc.team5976.robot;

import java.util.Vector;

public class SmartDashboardMap {
    // Drive Encoder
    private static final Vector<SmartValue> all = new Vector<>();
    public static final SmartValue kPFL = makeValue("P-Value FL", 0.04);
    public static final SmartValue kIFL = makeValue("I-Value FL", 0);
    public static final SmartValue kDFL = makeValue("D-Value FL", 0.4);
    public static final SmartValue kPFR = makeValue("P-Value FR", 0.04);
    public static final SmartValue kIFR = makeValue("I-Value FR", 0);
    public static final SmartValue kDFR = makeValue("D-Value FR", 0.4);
    public static final SmartValue kPRL = makeValue("P-Value RevL", 0.04);
    public static final SmartValue kIRL = makeValue("I-Value RevL", 0);
    public static final SmartValue kDRL = makeValue("D-Value RevL", 0.4);
    public static final SmartValue kPRR = makeValue("P-Value RevR", 0.04);
    public static final SmartValue kIRR = makeValue("I-Value RevR", 0);
    public static final SmartValue kDRR = makeValue("D-Value RevR", 0.4);
    public static final SmartValue ALLOWABLE_ERROR = makeValue("Allowable Error", 100);

    // Drive Talons
    public static final SmartValue PEAK_VOLTAGE = makeValue("Peak Voltage", 0.5);
    public static final SmartValue NOMINAL_VOLTAGE = makeValue("Nominal Voltage", 0.25);
    public static final SmartValue RAMP_RATE = makeValue("Ramp Rate", 0);
    public static final SmartValue SIDE_INVERSION = makeValue("Inverted Side", "RIGHT");

    // Lift Encoders
    public static final SmartValue LIFT_kP = makeValue("P-Value Lift", 0.04);
    public static final SmartValue LIFT_kI = makeValue("I-Value Lift", 0);
    public static final SmartValue LIFT_kD = makeValue("D-Value Lift", 0);
    public static final SmartValue LIFT_ALLOWABLE_ERROR = makeValue("Lift Allowable Error", 100);
    public static final SmartValue LIFT_PEAK_VOLTAGE = makeValue("Lift Peak Voltage", 0.5);
    public static final SmartValue LIFT_NOMINAL_VOLTAGE = makeValue("Lift Nominal Voltage", 0.6);

    public static final SmartValue POSITION = makeValue("Position", "RIGHT");
    public static final SmartValue DELAY = makeValue("Delay (Seconds)", 0);

    //Grabber Values
    public static final SmartValue GRABBER_POSITION_1 = makeValue("Grab Position 1", 0.55);
    public static final SmartValue GRABBER_POSITION_2 = makeValue("Grab Position 2", 0.57);
    public static final SmartValue GRABBER_PARTIAL_OPEN = makeValue("Grab Partial Position", 0.52);
    public static final SmartValue GRABBER_RANGE = makeValue("Grab Range", .01);

    public static void reportAll() {
        all.forEach(SmartDashboardMap::report);
    }

    private static SmartValue makeValue(String name, double defaultValue) {
        SmartValue value = new SmartValue(name, defaultValue);
        all.add(value);
        return value;
    }

    private static SmartValue makeValue(String name, String defaultValue) {
        SmartValue value = new SmartValue(name, defaultValue);
        all.add(value);
        return value;
    }

    private static void report(SmartValue variable) {
        System.out.println(variable.getKey() + ": " + variable.getDouble());
    }
}
