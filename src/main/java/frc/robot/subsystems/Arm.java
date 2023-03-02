package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.Constants.OutConstants;

public class Arm extends SubsystemBase {

  private CANSparkMax motor;
  private static Arm instance;

  private Arm() {
    motor = new CANSparkMax(Ports.ARM, MotorType.kBrushless);
    motor.setInverted(OutConstants.ARM_INVERTED);
  }

  public static Arm getInstance() {
    if (instance == null) {
      instance = new Arm();
    }
    return instance;
  }

  public void reach() {
    motor.set(OutConstants.ARM_SPEED);
  }

  public void retract() {
    motor.set(-OutConstants.ARM_SPEED);
  }

  public void stop() {
    motor.set(0);
  }

  @Override
  public void periodic() {
  }
}
