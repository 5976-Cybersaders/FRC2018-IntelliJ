package org.usfirst.frc.team5976.robot;

import edu.wpi.first.wpilibj.XboxController;
import org.usfirst.frc.team5976.robot.commands.DeployRampCommand;
import org.usfirst.frc.team5976.robot.commands.GrabberCommand;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a
    //// joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    private final XboxController DRIVE_CONTROLLER = new XboxController(0);
    private final XboxController SECONDARY_CONTROLLER = new XboxController(1);
    // Start the command when the button is released and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    private XBoxButton deployRamp, grabFlat, grabVertical, release, releasePartial;

    public OI(Robot robot) {
        grabFlat = new XBoxButton(DRIVE_CONTROLLER, XBoxButton.RawButton.LB);
        grabVertical = new XBoxButton(DRIVE_CONTROLLER, XBoxButton.RawButton.RB);
        release = new XBoxButton(DRIVE_CONTROLLER, XBoxButton.RawButton.A);
        releasePartial = new XBoxButton(DRIVE_CONTROLLER, XBoxButton.RawButton.X);

        deployRamp = new XBoxButton(SECONDARY_CONTROLLER, XBoxButton.RawButton.LB);

        grabFlat.whileHeld(new GrabberCommand(robot.getGrabberSubsystem(), 1, DRIVE_CONTROLLER));
        grabVertical.whileHeld(new GrabberCommand(robot.getGrabberSubsystem(), 2, DRIVE_CONTROLLER));
        release.whileHeld(new GrabberCommand(robot.getGrabberSubsystem(), 0, DRIVE_CONTROLLER));
        releasePartial.whileHeld(new GrabberCommand(robot.getGrabberSubsystem(), 3, DRIVE_CONTROLLER));

        deployRamp.whenPressed(new DeployRampCommand(robot.getRampSubsystem()));

    }

    public OI() {

    }


    public XboxController getDriveController() {
        return DRIVE_CONTROLLER;
    }

    public XboxController getSecondaryController() {
        return SECONDARY_CONTROLLER;
    }


}
