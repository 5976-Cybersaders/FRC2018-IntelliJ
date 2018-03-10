package org.usfirst.frc.team5976.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.SmartValue;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

public class EncoderTurnCommand extends AbstractEncoderDriveCommand {
    private double angle;
    private SmartValue smartValue;

    public EncoderTurnCommand(DriveTrain driveTrain, double angle) {
        super(driveTrain);
        this.angle = angle;

    }

    public EncoderTurnCommand(DriveTrain driveTrain, SmartValue smartValue) {
        super(driveTrain);
        this.smartValue = smartValue;

    }

    protected void initialize() {
        super.initialize();
        if (smartValue != null)
            angle = smartValue.getDouble();
        ticks = toTicks(angle);
        allowableError = (int) SmartDashboardMap.ALLOWABLE_ERROR.getDouble();
        int leftSlot = 1, rightSlot = 0;
        if (ticks > 0) {//Right Turn
            leftSlot = 0;
            rightSlot = 1;
        }
        leftMaster.selectProfileSlot(leftSlot, 0);
        rightMaster.selectProfileSlot(rightSlot, 0);

        System.out.println("Starting command drive turn angle " + angle + " ticks " + ticks + " Left=" + leftSlot + " Right=" + rightSlot);
        leftMaster.setSelectedSensorPosition(0, 0, 0);
        rightMaster.setSelectedSensorPosition(0, 0, 0);

        driveTrain.invertMotors();
        System.out.println("**********Right Master Inverted: " + rightMaster.getInverted());
        System.out.println("**********Right Slave Inverted: " + rightSlave.getInverted());
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
        rightMaster.set(ControlMode.Position, -ticks);
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

    protected double toTicks(double angle) {
        return super.toTicks((angle / 360) * (RobotMap.TURN_DIAMETER * Math.PI));
    }


}
