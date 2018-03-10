package org.usfirst.frc.team5976.robot.commands.autonomous;

import org.usfirst.frc.team5976.robot.Position;
import org.usfirst.frc.team5976.robot.Robot;

public class DeliverSwitchRightCommandGroup extends DeliverSwitchCommandGroup {
    public DeliverSwitchRightCommandGroup(Robot robot) {
        super(Position.RIGHT_POSITION, robot);
    }
}
