package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.Constants.OutConstants;


public class Claw extends SubsystemBase {
  private DoubleSolenoid clawy;
  private CANSparkMax wrist;
  private static Claw instance;
  // private AbsoluteEncoder wristEncoder;
  private RelativeEncoder wristEncoder;
  public boolean clawStatus;

  private Claw() {
    clawy = new DoubleSolenoid(Ports.PNEUMATIC_MODULE, PneumaticsModuleType.REVPH, Ports.CLAW_GRAB, Ports.CLAW_RELEASE);
    wrist = new CANSparkMax(Ports.WRIST, MotorType.kBrushless);
    // wristEncoder = wrist.getAbsoluteEncoder(Type.kDutyCycle);
    wristEncoder = wrist.getEncoder();
    clawStatus = true;
  }

  public static Claw getInstance() {
    if (instance == null) {
      instance = new Claw();
    }
    return instance;
  }

  public void grab() {
    clawy.set(Value.kForward);
  }
  
  public void release() {
    clawy.set(Value.kReverse);
  }

  public double getWristPosition() {
    return wristEncoder.getPosition();
  }

  public void wristReset() {
    wristEncoder.setPosition(0);
  }

  /**
   * Towards the front of the chassis
   */
 public void forward() {
  wrist.set(OutConstants.WRIST_SPEED);
 }
public void move(double speed) {
  wrist.set(speed);

}
 /**
  * Towards the back of the chassis
  */
 public void backward(){
  wrist.set(-OutConstants.WRIST_SPEED);
 }

 public void stop() {
  wrist.set(0);
 }
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Wrist Position", getWristPosition());
    SmartDashboard.putBoolean("CLAW STATUS", clawStatus);
  }
}
