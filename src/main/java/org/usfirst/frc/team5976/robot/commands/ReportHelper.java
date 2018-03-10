package org.usfirst.frc.team5976.robot.commands;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.StickyFaults;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Command;

import java.text.DecimalFormat;

public class ReportHelper {
    private static DecimalFormat formatter = new DecimalFormat("#.000000");

    public static void reportExecute(WPI_TalonSRX talon, String side, int stableCount, double avgError) {
        double set = talon.getClosedLoopTarget(0);
        double pos = talon.getSelectedSensorPosition(0);
        int clErr = talon.getClosedLoopError(0);
        double voltage = talon.getMotorOutputVoltage();

        System.out.println(side + " Set: " + formatter.format(set) +
                "\tPos: " + formatter.format(pos) +
                "\tCLErr: " + clErr +

                " Count: " + stableCount + " Avg Error: " + avgError + " Voltage: " + formatter.format(voltage) +
                " Percent Output: " + talon.getMotorOutputPercent());

    }

    public static void report(WPI_TalonSRX talon, Command command, String name) {
        System.out.println("INIT " + command + " Talon: " + name);
        StickyFaults stickyFault = new StickyFaults();
        ErrorCode errorCode = talon.getStickyFaults(stickyFault);
        System.out.println("Sticky Faults: " + stickyFault.hasAnyFault() + " Error Code: " + errorCode);
        System.out.println("Control Mode: " + talon.getControlMode());
        System.out.println("Device ID" + talon.getDeviceID());
        System.out.println("Inverted: " + talon.getInverted());
        System.out.println("Position: " + talon.getSelectedSensorPosition(0));
        if (name.toLowerCase().endsWith("master")) {
            System.out.println("Closed Loop Target: " + talon.getClosedLoopTarget(0));
            System.out.println("Closed Loop Error: " + talon.getClosedLoopError(0));
        }
        System.out.println();
    }

    public static void reportTeleOp(WPI_TalonSRX talon, String name) {
        System.out.println(name +
                " Voltage: " + formatter.format(talon.getMotorOutputVoltage()) +
                "\tPercent Output: " + talon.getMotorOutputPercent());
    }
}
