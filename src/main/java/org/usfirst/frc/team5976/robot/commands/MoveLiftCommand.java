package org.usfirst.frc.team5976.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public abstract class MoveLiftCommand extends Command {
    protected WPI_TalonSRX talon;
    protected double setPoint;
    protected int counter = 0;

    public MoveLiftCommand(LiftSubsystem liftSubsystem, int setPoint) {  //  only for autonomous
        talon = liftSubsystem.getLiftTalon();
        this.setPoint = setPoint;
        requires(liftSubsystem);
    }

    @Override
    protected void initialize() {
        counter = 0;
        System.out.println("$$$$$$$$$$$tart " + getClass().getSimpleName() + " set point: " + setPoint + " Position: "
                + talon.getSelectedSensorPosition(0));
    }

    @Override
    protected void execute() {
        talon.set(ControlMode.Position, setPoint);
        counter++;
    }

    protected double toTicks(double inches) {
        return inches / (Math.PI * 2.5) * 4096;
    }
}
