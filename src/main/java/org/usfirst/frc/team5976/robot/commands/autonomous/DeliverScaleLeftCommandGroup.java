package org.usfirst.frc.team5976.robot.commands.autonomous;

import org.usfirst.frc.team5976.robot.Position;
import org.usfirst.frc.team5976.robot.Robot;

public class DeliverScaleLeftCommandGroup extends DeliverScaleCommandGroup {
    public DeliverScaleLeftCommandGroup(Robot robot) {
        super(Position.LEFT_POSITION, robot);
    }
}
