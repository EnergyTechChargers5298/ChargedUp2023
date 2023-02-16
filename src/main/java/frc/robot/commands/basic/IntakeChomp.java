// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.basic;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeChomp extends CommandBase {
  private Intake intake;

  /** Creates a new IntakeChomp. */
  public IntakeChomp() {
    intake = Intake.getInstance();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.chomp();
  }
}
