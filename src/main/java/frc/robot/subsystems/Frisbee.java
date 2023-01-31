// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;

public class Frisbee extends SubsystemBase {
  private CANSparkMax frisbeeMotor;
  private static Frisbee instance;
  /** Creates a new frisbee. */
  public Frisbee() {
    frisbeeMotor=new CANSparkMax(Ports.FRISBEE_PORT, MotorType.kBrushless);
    frisbeeMotor.setInverted(false);
  }

  public static Frisbee getInstance() {
    if (instance == null) {
      instance = new Frisbee();
    }
    return instance;
  }

  public void clockwise() {
    frisbeeMotor.set(1);
  }

  public void counterclockwise() {
    frisbeeMotor.set(-1);
  }

  public void stop() {
    frisbeeMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
