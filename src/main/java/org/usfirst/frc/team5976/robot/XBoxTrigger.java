package org.usfirst.frc.team5976.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

public class XBoxTrigger implements CMHDigitalInput {

    private XboxController controller;
    private GenericHID.Hand hand;

    public XBoxTrigger(XboxController controller, GenericHID.Hand hand) {
        this.controller = controller;
        this.hand = hand;
    }

    @Override
    public boolean get() {
        return controller.getTriggerAxis(hand) > 0.5;
    }
}
