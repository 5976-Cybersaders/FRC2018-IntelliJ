package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.commands.EncoderInitCommand;
import org.usfirst.frc.team5976.robot.commands.GrabCubeCommand;
import org.usfirst.frc.team5976.robot.commands.LiftTalonInitializationCommand;

public class AutonomousInitializationCommandGroup extends CommandGroup {
    public AutonomousInitializationCommandGroup(Robot robot) {
        addParallel(new EncoderInitCommand(robot.getDriveTrain()));
        addParallel(new LiftTalonInitializationCommand(robot.getLiftSubsystem()));
        addParallel(new GrabCubeCommand(robot.getGrabberSubsystem(), 1));
        //TODO: Find how long grabber takes to grab cube at the start
    }
}