package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.Position;
import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.*;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class DeliverSwitchCommandGroup extends CommandGroup {
    private DriveTrain driveTrain;
    private GrabberSubsystem grabberSubsystem;
    private LiftSubsystem liftSubsystem;
    private int position;


    public DeliverSwitchCommandGroup(Position position, Robot robot) {
        driveTrain = robot.getDriveTrain();
        grabberSubsystem = robot.getGrabberSubsystem();
        liftSubsystem = robot.getLiftSubsystem();
        this.position = position.getIntPosition();

        addSequential(new AutonomousInitializationCommandGroup(robot));
        addSequential(new CombinedCommandGroup());
        addSequential(new WaitForReleaseCompleted());
        addSequential(new CombinedPostCommandGroup());

    }

    class CombinedCommandGroup extends CommandGroup {
        public CombinedCommandGroup() {
            addParallel(RaiseLiftCommand.RaiseLiftToSwitch(liftSubsystem));
            addParallel(new MainCommandGroup());
        }
    }

    class MainCommandGroup extends CommandGroup {
        public MainCommandGroup() {
            addSequential(new DoNothingCommand(SmartDashboardMap.DELAY));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 148));
            addSequential(new EncoderTurnCommand(driveTrain, -90 * position));
            addSequential(new WaitForLiftRaised());
            //TimeOut is here because there is a possibility of hitting the fence
            // before driving the full distance and command will not end
            addSequential(new EncoderDriveStraightCommand(driveTrain, 36.56, 3)); //TODO: Check tiemout value
            addSequential(new ReleaseCubeCommand(grabberSubsystem, 1));
        }
    }

    class PostReleaseCommandGroup extends CommandGroup {
        public PostReleaseCommandGroup() {
            addSequential(new EncoderDriveStraightCommand(driveTrain, -5));
            addSequential(new EncoderTurnCommand(driveTrain, -90 * position));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 50));
            addSequential(new EncoderTurnCommand(driveTrain, 90 * position));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 50));
        }
    }

    class CombinedPostCommandGroup extends CommandGroup {
        public CombinedPostCommandGroup() {
            addParallel(new ReturnLiftToStartPointCommand(liftSubsystem));
            addParallel(new PostReleaseCommandGroup());
        }
    }
}
