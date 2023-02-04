package frc.robot.subsystems;


import edu.wpi.first.networktables.BooleanSubscriber;
import edu.wpi.first.networktables.DoubleSubscriber;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.utils.LEDColors.Colors;

public class LEDStatus extends SubsystemBase {
    private final Spark led;
    private final NetworkTableInstance netTable;
    private final NetworkTable FMSTable;
    private final DoubleSubscriber FMSControlData;
    private final BooleanSubscriber IsRedAlliance;
    private static LEDStatus instance;

    private LEDStatus() {
        led = new Spark(Ports.BLINKIN_PORT);
        netTable = NetworkTableInstance.getDefault();
        FMSTable = netTable.getTable("FMSInfo");
        FMSControlData = FMSTable.getDoubleTopic("FMSControlData").subscribe(0);
        IsRedAlliance = FMSTable.getBooleanTopic("IsRedAlliance").subscribe(false);
    }

        public static LEDStatus getInstance() {
            if(instance == null) {
                instance = new LEDStatus();
            }
            return instance;
        }
  
    public void setLed(Colors color) {
        led.set(color.getColor());
    }


    public void RobotStatus(){
        if(FMSControlData.get() == 33){
            setLed(Colors.STROBE_GOLD);
        }
        else{

            if(IsRedAlliance.get()){
                setLed(Colors.STROBE_RED);
            }
            else{
                setLed(Colors.STROBE_BLUE);
            }
        }
    }    
    @Override
    public void periodic() {
        RobotStatus();
    }
}