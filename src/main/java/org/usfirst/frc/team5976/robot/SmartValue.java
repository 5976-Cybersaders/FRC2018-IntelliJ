package org.usfirst.frc.team5976.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartValue {
    private String key;
    private double defaultValue;
    private String defaultString;

    public SmartValue(String key, double defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
        SmartDashboard.putNumber(key, defaultValue);
    }

    public SmartValue(String key, String defaultString) {
        this.key = key;
        this.defaultString = defaultString;
        SmartDashboard.putString(key, defaultString);
    }

    public double getValue() {
        return SmartDashboard.getNumber(key, defaultValue);
    }

    public int getIntValue() {
        return (int) getValue();
    }

    public String getKey() {
        return key;
    }

    public double getDouble() {
        return getValue();
    }

    public String getString() {
        return SmartDashboard.getString(key, defaultString);
    }
}
