// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;


public class ClawClose extends CommandBase {
  private Claw claw;
  /** Creates a new ClawClosed. */
  public ClawClose() {
    // Use addRequirements() here to declare subsystem dependencies.
    claw = Claw.getInstance();
    addRequirements(claw);
  }

  private void addRequirements(Claw claw2) {
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    claw.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    claw.ClawClose();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    claw.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
