// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.Constants.InConstants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  private CANSparkMax intakeMotor;
  private static Intake instance;
  private DoubleSolenoid mouth;

  private Intake() {
    intakeMotor = new CANSparkMax(Ports.INTAKE, MotorType.kBrushless);
    intakeMotor.setInverted(InConstants.INTAKE_INVERTED);
    mouth = new DoubleSolenoid(Ports.PNEUMATIC_MODULE, PneumaticsModuleType.REVPH, Ports.INTAKE_CHOMP, Ports.INTAKE_RETRACT);
  }

  public static Intake getInstance() {
    if (instance == null) {
      instance = new Intake();
    }
    return instance;
  }

  public void eat() {
    intakeMotor.set(InConstants.INTAKE_SPEED);
  }

  public void spit() {
    intakeMotor.set(-InConstants.INTAKE_SPEED);
  }

  public void stop() {
    intakeMotor.set(0);
  }

  public void chomp() {
    mouth.set(Value.kForward);
  }

  public void retract() {
    mouth.set(Value.kReverse);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
