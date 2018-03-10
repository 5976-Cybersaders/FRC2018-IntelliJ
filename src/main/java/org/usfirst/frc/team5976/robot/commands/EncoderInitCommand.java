package org.usfirst.frc.team5976.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

public class EncoderInitCommand extends Command {
    private WPI_TalonSRX leftMaster, rightMaster, leftSlave, rightSlave;

    public EncoderInitCommand(DriveTrain driveTrain) {
        leftMaster = driveTrain.getLeftMaster();
        rightMaster = driveTrain.getRightMaster();
        leftSlave = driveTrain.getLeftSlave();
        rightSlave = driveTrain.getRightSlave();
        requires(driveTrain);
    }

    protected void initialize() {
        System.out.println("BEGIN AUTONOMOUS ENCODER INIT");
        initMaster(leftMaster, -1);
        initMaster(rightMaster, 1);

        SmartDashboardMap.reportAll();
        report(leftMaster, "Left Master");
        report(rightMaster, "Right Master");
        report(leftSlave, "Left Slave");
        report(rightSlave, "Right Slave");
    }

    protected void report(WPI_TalonSRX talon, String name) {
        ReportHelper.report(talon, this, name);
    }

    protected void initMaster(WPI_TalonSRX talon, int side) {
        int slotIndex = 1;
        int stupidTimeOut = 0;

        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, stupidTimeOut);
        talon.setSelectedSensorPosition(0, 0, stupidTimeOut);
        talon.set(ControlMode.Position, stupidTimeOut);

        double peakOutput = SmartDashboardMap.PEAK_VOLTAGE.getDouble();
        double nominalOutput = SmartDashboardMap.NOMINAL_VOLTAGE.getDouble();

        talon.selectProfileSlot(slotIndex, stupidTimeOut);
        talon.configAllowableClosedloopError(slotIndex, SmartDashboardMap.ALLOWABLE_ERROR.getIntValue(), stupidTimeOut);
        talon.configPeakOutputForward(peakOutput, stupidTimeOut);
        talon.configPeakOutputReverse(-peakOutput, stupidTimeOut);
        talon.configNominalOutputForward(nominalOutput, stupidTimeOut);
        talon.configNominalOutputReverse(-nominalOutput, stupidTimeOut);
        talon.configClosedloopRamp(SmartDashboardMap.RAMP_RATE.getDouble(), stupidTimeOut);

        if (side == -1) {//Left
            System.out.println("INIT LEFT PIDs");
            talon.config_kP(slotIndex, SmartDashboardMap.kPRL.getDouble(), stupidTimeOut);
            talon.config_kI(slotIndex, SmartDashboardMap.kIRL.getDouble(), stupidTimeOut);
            talon.config_kD(slotIndex, SmartDashboardMap.kDRL.getDouble(), stupidTimeOut);

            slotIndex = 0;
            talon.selectProfileSlot(slotIndex, stupidTimeOut);
            talon.config_kP(slotIndex, SmartDashboardMap.kPFL.getDouble(), stupidTimeOut);
            talon.config_kI(slotIndex, SmartDashboardMap.kIFL.getDouble(), stupidTimeOut);
            talon.config_kD(slotIndex, SmartDashboardMap.kDFL.getDouble(), stupidTimeOut);

        } else {
            System.out.println("INIT RIGHT PIDs");
            talon.config_kP(slotIndex, SmartDashboardMap.kPRR.getDouble(), stupidTimeOut);
            talon.config_kI(slotIndex, SmartDashboardMap.kIRR.getDouble(), stupidTimeOut);
            talon.config_kD(slotIndex, SmartDashboardMap.kDRR.getDouble(), stupidTimeOut);

            slotIndex = 0;
            talon.selectProfileSlot(slotIndex, stupidTimeOut);
            talon.config_kP(slotIndex, SmartDashboardMap.kPFR.getDouble(), stupidTimeOut);
            talon.config_kI(slotIndex, SmartDashboardMap.kIFR.getDouble(), stupidTimeOut);
            talon.config_kD(slotIndex, SmartDashboardMap.kDFR.getDouble(), stupidTimeOut);
        }

        // Profile 0 For Both
        talon.configPeakOutputForward(peakOutput, stupidTimeOut);
        talon.configPeakOutputReverse(-peakOutput, stupidTimeOut);
        System.out.println("PEAK OUTPUT ----------->" + peakOutput);
        System.out.println("!!!!!!! NOMINAL ------------->" + nominalOutput);
        System.out.println("Setting allowable error to " + SmartDashboardMap.ALLOWABLE_ERROR.getDouble());
        talon.configAllowableClosedloopError(slotIndex, SmartDashboardMap.ALLOWABLE_ERROR.getIntValue(), stupidTimeOut);
        talon.configClosedloopRamp(SmartDashboardMap.RAMP_RATE.getDouble(), stupidTimeOut);

        talon.configNominalOutputForward(nominalOutput, stupidTimeOut);
        talon.configNominalOutputReverse(-nominalOutput, stupidTimeOut);

    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
