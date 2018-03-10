package org.usfirst.frc.team5976.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

public abstract class AbstractEncoderDriveCommand extends Command {
    // Wheel Values
    private final int STABLE_COUNT_STOP = 20;
    private final int DIAMETER = 6;
    protected DriveTrain driveTrain;
    protected WPI_TalonSRX leftMaster, leftSlave, rightMaster, rightSlave;
    protected double ticks;
    protected int stableCount;
    protected int printCounter = 1, printInterval = 1;
    protected int allowableError = 100;
    protected boolean inversion = true;
    private double previousError;
    private double currentError;

    public AbstractEncoderDriveCommand(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
        leftMaster = driveTrain.getLeftMaster();
        leftSlave = driveTrain.getLeftSlave();
        rightMaster = driveTrain.getRightMaster();
        rightSlave = driveTrain.getRightSlave();

        ticks = 0;
        stableCount = 0;
        previousError = 9999999;
        requires(driveTrain);
    }

    @Override
    protected void initialize() {
        stableCount = 0;
        previousError = 1000000;

        report(driveTrain.getLeftMaster(), "Left Master");
        report(driveTrain.getLeftSlave(), "Left Slave");
        report(driveTrain.getRightMaster(), "Right Master");
        report(driveTrain.getRightSlave(), "Right Slave");
    }

    protected void report(WPI_TalonSRX talon, String name) {
        ReportHelper.report(talon, this, name);
    }

    protected void reportExecute(WPI_TalonSRX talon, String side) {
        ReportHelper.reportExecute(talon, side, stableCount, currentError);
    }

    protected double toTicks(double inches) {
        return inches / (Math.PI * DIAMETER) * 4096;
    }

    @Override
    protected boolean isFinished() {
        currentError = (Math.abs(leftMaster.getClosedLoopError(0)) + Math.abs(rightMaster.getClosedLoopError(0))) / 2;
        if ((currentError <= allowableError && previousError > allowableError)) {
            System.out.println("Crossed into allowable error zone after stable count: " + stableCount);
        } else if (currentError > allowableError && previousError <= allowableError) {
            System.out.println("Crossed out of allowable error zone stable count: " + stableCount + " Previous: " + previousError + " current: " + currentError);
        }
        previousError = currentError;
        if (currentError <= allowableError) {
            stableCount++;
            if (stableCount >= STABLE_COUNT_STOP) {
                System.out.println("Stopping. Stable count = " + stableCount + " Left: "
                        + Math.abs(leftMaster.getClosedLoopError(0)) + " Right: " + Math.abs(rightMaster.getClosedLoopError(0)));
                return true;
            }
        } else {
            stableCount = 0;
        }
        return false;
    }

    protected void end() {
        System.out.println("END " + this);
    }

    protected void interrupted() {
        end();
    }
}
