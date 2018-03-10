package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.DoNothingCommand;
import org.usfirst.frc.team5976.robot.commands.EncoderDriveStraightCommand;
import org.usfirst.frc.team5976.robot.commands.EncoderInitCommand;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

public class CrossLineCommandGroup extends CommandGroup {

    public CrossLineCommandGroup(Robot robot) {
        DriveTrain driveTrain = robot.getDriveTrain();
        addSequential(new EncoderInitCommand(driveTrain));
        addSequential(new DoNothingCommand(SmartDashboardMap.DELAY));
        addSequential(new EncoderDriveStraightCommand(driveTrain, 90));
    }

}
