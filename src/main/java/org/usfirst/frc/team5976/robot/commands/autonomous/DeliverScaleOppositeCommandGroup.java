package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.Position;
import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.*;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class DeliverScaleOppositeCommandGroup extends CommandGroup{

    private DriveTrain driveTrain;
    private GrabberSubsystem grabberSubsystem;
    private LiftSubsystem liftSubsystem;
    private int position;


    public DeliverScaleOppositeCommandGroup(int position, Robot robot) {
        driveTrain = robot.getDriveTrain();
        grabberSubsystem = robot.getGrabberSubsystem();
        liftSubsystem = robot.getLiftSubsystem();
        this.position = position;

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
            addSequential(new EncoderDriveStraightCommand(driveTrain, 209.73));
            addSequential(new EncoderTurnCommand(driveTrain, -90 * position));
            //TimeOut is here because there is a possibility of hitting the fence
            // before driving the full distance and command will not end
            addSequential(new EncoderDriveStraightCommand(driveTrain, 190));
            addSequential(new EncoderTurnCommand(driveTrain, -90 * position));
            addSequential(new WaitForLiftRaised());
            addSequential(new EncoderDriveStraightCommand(driveTrain, 54, 3)); //TODO: Check tiemout value
            addSequential(new ReleaseCubeCommand(grabberSubsystem, 1));
        }
    }

    class PostReleaseCommandGroup extends CommandGroup {
        public PostReleaseCommandGroup() {
            addSequential(new EncoderDriveStraightCommand(driveTrain, -49));
            addSequential(new EncoderTurnCommand(driveTrain, -180 * position));
        }
    }

    class CombinedPostCommandGroup extends CommandGroup {
        public CombinedPostCommandGroup() {
            addParallel(new ReturnLiftToStartPointCommand(liftSubsystem));
            addParallel(new PostReleaseCommandGroup());
        }
    }

    /*
    Drive 209.735 = 228.735-19
    Turn 90
    Drive 192.62 = 323.28 - 47.19 - 83.57
    changed to 190 in code
    Turn -90
    Drive 51.915 = 299.65 - 209.735
    Changed  to 54 in code
    ****Not yet added 3 for bumpers

    47.19 is the center of the robot from the side wall at start
    83.57 is distance from the wall to scale + 1 foot to drop cube towards outer edge of plate
     */
}
