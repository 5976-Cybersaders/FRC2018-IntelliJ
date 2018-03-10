package org.usfirst.frc.team5976.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5976.robot.OI;
import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.commands.TeleOpLiftCommand;

public class LiftSubsystem extends Subsystem {
    private OI oi;
    private WPI_TalonSRX talon;

    public LiftSubsystem(OI oi) {
        this.oi = oi;
        this.talon = new WPI_TalonSRX(RobotMap.LIFT_TALON_ID);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void initDefaultCommandForTeleOp() {
        setDefaultCommand(new TeleOpLiftCommand(this, oi.getSecondaryController()));
    }

    public WPI_TalonSRX getLiftTalon() {
        return talon;
    }
}
