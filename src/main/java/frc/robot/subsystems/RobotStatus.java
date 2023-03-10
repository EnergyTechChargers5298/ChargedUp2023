package frc.robot.subsystems;


import edu.wpi.first.networktables.BooleanSubscriber;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.utils.LEDColors;

public class RobotStatus extends SubsystemBase {
    // Instance of the subsystem
    private static RobotStatus instance;

    // The led controller
    private Spark led;

    // The Network Table
    private NetworkTableInstance nTableInst;
    
    // All Data Subscriptions
    private BooleanSubscriber redAlliance;

    private RobotStatus() {
        // Led controller
        led = new Spark(Ports.BLINKIN);

        // Network Table
        nTableInst = NetworkTableInstance.getDefault();

        // All Data Subscriptions
        redAlliance = nTableInst.getBooleanTopic("FMSInfo/IsRedAlliance").subscribe(false);
    }

    public static RobotStatus getInstance() {
        if (instance == null) {
            instance = new RobotStatus();
        }
        return instance;
    }

    private void setColor(LEDColors color) {
        led.set(color.sparkValue);
    }

    private void ledStatus() {
        if(redAlliance.get()) {
            setColor(LEDColors.RED_SHOT);
        } else {
            setColor(LEDColors.BLUE_SHOT);
        }
    }

    @Override
    public void periodic() {
        ledStatus();
    }

}
