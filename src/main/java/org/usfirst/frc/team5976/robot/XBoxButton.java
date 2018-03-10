package org.usfirst.frc.team5976.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

public class XBoxButton extends Button implements CMHDigitalInput {
    XboxController xBox;
    int buttonNumber;

    public XBoxButton(XboxController xBox, int buttonNumber) {
        this.xBox = xBox;
        this.buttonNumber = buttonNumber;
    }

    public boolean get() {
        return xBox.getRawButton(buttonNumber);
    }

    public class RawButton {
        public static final int A = 1;
        public static final int B = 2;
        public static final int X = 3;
        public static final int Y = 4;
        public static final int LB = 5;
        public static final int RB = 6;
        public static final int BACK_BUTTON = 7;
        public static final int START_BUTTON = 8;
        public static final int LEFT_STICK = 9;
        public static final int RIGHT_STICK = 10;
    }
}
