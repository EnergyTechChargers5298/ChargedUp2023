// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.Constants.InConstants;

public class Frisbee extends SubsystemBase {
  private CANSparkMax frisbeeMotor;
  private static Frisbee instance;

  /** Creates a new lazysusan. */
  private Frisbee() {
    frisbeeMotor = new CANSparkMax(Ports.FRISBEE, MotorType.kBrushless);
    frisbeeMotor.setInverted(InConstants.FRISBEE_INVERTED);
  }

  public static Frisbee getInstance() {
    if (instance == null) {
      instance = new Frisbee();
    }
    return instance;
  }

  public void clockwise() {
    frisbeeMotor.set(InConstants.FRISBEE_SPEED);
  }

  public void counterClockwise() {
    frisbeeMotor.set(-InConstants.FRISBEE_SPEED);
  }

  public void stop() {
    frisbeeMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
