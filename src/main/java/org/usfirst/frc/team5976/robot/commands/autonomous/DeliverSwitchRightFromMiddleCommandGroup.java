package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.commands.EncoderDriveStraightCommand;
import org.usfirst.frc.team5976.robot.commands.RaiseLiftCommand;
import org.usfirst.frc.team5976.robot.commands.ReleaseCubeCommand;
import org.usfirst.frc.team5976.robot.commands.WaitForLiftRaised;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class DeliverSwitchRightFromMiddleCommandGroup extends CommandGroup {
    private DriveTrain driveTrain;
    private LiftSubsystem liftSubsystem;
    private GrabberSubsystem grabberSubsystem;

    public DeliverSwitchRightFromMiddleCommandGroup(Robot robot) {
        driveTrain = robot.getDriveTrain();
        liftSubsystem = robot.getLiftSubsystem();
        grabberSubsystem = robot.getGrabberSubsystem();

        addSequential(new AutonomousInitializationCommandGroup(robot));
        addSequential(new CombinedCommandGroup());
    }

    class CombinedCommandGroup extends CommandGroup {
        public CombinedCommandGroup() {
            addParallel(RaiseLiftCommand.RaiseLiftToScale(liftSubsystem));
            addParallel(new MainCommandGroup());
        }
    }

    class MainCommandGroup extends CommandGroup {
        public MainCommandGroup() {
            addSequential(new EncoderDriveStraightCommand(driveTrain, 102, 5)); //TODO: Check tiemout
            addSequential(new WaitForLiftRaised());
            addSequential(new ReleaseCubeCommand(grabberSubsystem, 1));
        }
    }
}
