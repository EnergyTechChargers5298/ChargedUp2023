// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.basic;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Claw;
public class ClawLow extends CommandBase {

private Claw claw;

  /** Creates a new ClawLow. */
  public ClawLow() {
    // Use addRequirements() here to declare subsystem dependencies.
    claw = Claw.getInstance();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    claw.SetLow();
  }
}
