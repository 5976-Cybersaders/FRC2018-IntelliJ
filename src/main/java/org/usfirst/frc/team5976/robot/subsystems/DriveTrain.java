package org.usfirst.frc.team5976.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5976.robot.OI;
import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.TeleOpTankDrive;

import java.util.Arrays;
import java.util.List;

public class DriveTrain extends Subsystem {
    private WPI_TalonSRX leftMaster, leftSlave, rightMaster, rightSlave;
    private List<WPI_TalonSRX> leftTalons, rightTalons;
    private OI oi;

    public DriveTrain(OI oi) {
        super();
        System.out.println("START INIT DriveTrain");
        leftMaster = new WPI_TalonSRX(RobotMap.LEFT_MASTER_TALON_ID);
        leftSlave = new WPI_TalonSRX(RobotMap.LEFT_SLAVE_TALON_ID);
        rightMaster = new WPI_TalonSRX(RobotMap.RIGHT_MASTER_TALON_ID);
        rightSlave = new WPI_TalonSRX(RobotMap.RIGHT_SLAVE_TALON_ID);

        leftTalons = Arrays.asList(new WPI_TalonSRX[]{leftMaster, leftSlave});
        rightTalons = Arrays.asList(new WPI_TalonSRX[]{rightMaster, rightSlave});


        this.oi = oi;
        System.out.println("END INIT DriveTrain");
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void updateDefaultCommandForNonTeleOp() {
        //If you see an error like "Output not updated often" change this value
        //robotDrive.setExpiration(MotorSafety.DEFAULT_SAFETY_EXPIRATION);
        setDefaultCommand(null);
    }

    public void updateDefaultCommandForTeleOp() {
        //If you see an error like "Output not updated often" change this value
        //robotDrive.setExpiration(MotorSafety.DEFAULT_SAFETY_EXPIRATION);
        setDefaultCommand(new TeleOpTankDrive(getOI().getDriveController(), this));
    }

    public void invertMotors() {
        List<WPI_TalonSRX> talonsToInvert = rightTalons, talonsToNotInvert = leftTalons;
        if (SmartDashboardMap.SIDE_INVERSION.getString().toUpperCase().equals("LEFT")) {
            talonsToInvert = leftTalons;
            talonsToNotInvert = rightTalons;
        }
        talonsToInvert.forEach(talon -> {
            talon.setSensorPhase(true);
            talon.setInverted(true);
        });
        talonsToNotInvert.forEach(talon -> {
            talon.setSensorPhase(true);
            talon.setInverted(false);
        });
    }

    public WPI_TalonSRX getLeftMaster() {
        return leftMaster;
    }

    public WPI_TalonSRX getRightMaster() {
        return rightMaster;
    }

    public WPI_TalonSRX getLeftSlave() {
        return leftSlave;
    }

    public WPI_TalonSRX getRightSlave() {
        return rightSlave;
    }

    public OI getOI() {
        return oi;
    }
}
