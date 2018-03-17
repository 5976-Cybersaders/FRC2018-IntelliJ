package org.usfirst.frc.team5976.robot;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5976.robot.commands.autonomous.*;

public class GameData {
    private String data;
    private Robot robot;

    public GameData(Robot robot, String data) {
        this.data = data;
        this.robot = robot;
    }

    private boolean isAllianceSwitchLeft() {
        return data.charAt(0) == 'L';
    }

    private boolean isAllianceSwitchRight() {
        return !isAllianceSwitchLeft();
    }

    private boolean isScaleLeft() {
        return data.charAt(1) == 'L';
    }

    private boolean isScaleRight() {
        return !isScaleLeft();
    }

    public Command getCommand() {
        Command command;
        int position = Position.getPosition().getIntPosition();
        System.out.println("Position Value:" + position);
        switch (position) {
            case Position.LEFT:
                command = getCommandLeft();
                break;
            case Position.MIDDLE:
                command = getCommandMiddle();
                break;
            case Position.RIGHT:
                command = getCommandRight();
                break;
            case Position.TEST:
                command = new TestCommandGroup(robot);
                break;
            default:
                command = new CrossLineCommandGroup(robot);
                break;
        }
        System.out.println("Running " + command.getClass().getSimpleName());
        return command;
    }

    private Command getCommandLeft() {
        if (isAllianceSwitchLeft()) return new DeliverSwitchLeftCommandGroup(robot);
        if (isScaleLeft()) return new DeliverScaleLeftCommandGroup(robot);
        return new DeliverScaleOppositeCommandGroup(Position.LEFT, robot);
    }

    private Command getCommandMiddle() {
        if (isAllianceSwitchLeft())
            return new DeliverSwitchLeftFromMiddleCommandGroup(robot);
        return new DeliverSwitchRightFromMiddleCommandGroup(robot);
    }

    private Command getCommandRight() {
        if (isAllianceSwitchRight()) return new DeliverSwitchRightCommandGroup(robot);
        if (isScaleRight()) return new DeliverScaleRightCommandGroup(robot);
        return new DeliverScaleOppositeCommandGroup(Position.RIGHT, robot);
    }
}
