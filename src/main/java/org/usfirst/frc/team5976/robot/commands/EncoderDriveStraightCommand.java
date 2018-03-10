package org.usfirst.frc.team5976.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

public class EncoderDriveStraightCommand extends AbstractEncoderDriveCommand {

    private double inches;

    public EncoderDriveStraightCommand(DriveTrain driveTrain, double inches) {
        super(driveTrain);
        System.out.println("Received inches: " + inches);
        this.inches = inches;
    }

    protected void initialize() {
        super.initialize();
        ticks = toTicks(inches);
        System.out.println("Using inches " + inches + " ticks ------>" + ticks);
        allowableError = (int) SmartDashboardMap.ALLOWABLE_ERROR.getDouble();
        if (ticks > 0) {
            leftMaster.selectProfileSlot(0, 0);
            rightMaster.selectProfileSlot(0, 0);
        } else {
            leftMaster.selectProfileSlot(1, 0);
            rightMaster.selectProfileSlot(1, 0);
        }
        System.out.println("Starting command drive straight inches " + inches + " Ticks " + ticks);
        leftMaster.setSelectedSensorPosition(0, 0, 0);
        rightMaster.setSelectedSensorPosition(0, 0, 0);

        driveTrain.invertMotors();

        report(leftMaster, "Left Master");
        report(rightMaster, "Right Master");
        report(leftSlave, "Left Slave");
        report(rightSlave, "Right Slave");

    }

    protected void execute() {
        SmartDashboard.putNumber("Left Revolutions", leftMaster.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("Right Revolutions", rightMaster.getSelectedSensorPosition(0));
        leftMaster.set(ControlMode.Position, ticks);
        leftSlave.follow(leftMaster);
        rightMaster.set(ControlMode.Position, ticks);
        rightSlave.follow(rightMaster);

        if (printCounter == printInterval) {
            reportExecute(leftMaster, "Left Master");
            reportExecute(rightMaster, "Right Master");
            System.out.println();
            printCounter = 0;
        } else {
            printCounter++;
        }

    }
}
