package frc.robot.utils;

import edu.wpi.first.wpilibj.XboxController;

public class SparkyController extends XboxController {
    public static final double AXIS_THRESHOLD = 0.5;

    public SparkyController(int port) {
        super(port);
    }

    public boolean getLeftTriggerButton() {
        return getRawAxis(Axis.kLeftTrigger.value) > AXIS_THRESHOLD;
    }

    public boolean getRightTriggerButton() {
        return getRawAxis(Axis.kRightTrigger.value) > AXIS_THRESHOLD;
    }

    public boolean getDPad(int pov) {
        return getPOV(pov) == pov;
    }
}
