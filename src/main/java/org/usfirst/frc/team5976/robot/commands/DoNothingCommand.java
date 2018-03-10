package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5976.robot.SmartValue;

public class DoNothingCommand extends Command {
    private SmartValue delay;

    public DoNothingCommand(SmartValue delay) {
        this.delay = delay;
    }

    @Override
    protected void initialize() {
        double delaySeconds = delay.getDouble();
        setTimeout(delaySeconds);
        System.out.println("Delay: " + delaySeconds);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
}
