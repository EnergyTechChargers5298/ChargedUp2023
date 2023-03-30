// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.complex;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AntiTip extends CommandBase {
  private Drivetrain drivetrain;
  private float setpoint = 0;
  /** Creates a new AntiTip. */
  public AntiTip() {
    drivetrain = Drivetrain.getInstance();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.getPitch();
    drivetrain.drive(0, 0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(drivetrain.getPitch() > setpoint) {
      drivetrain.drive(0, 0.5, 0);
    } else if(drivetrain.getPitch() < setpoint) {
      drivetrain.drive(0, -0.5, 0);
    } else {
      drivetrain.drive(0, 0, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(setpoint == drivetrain.getPitch()) {
      return true;
    }
    return false;
  }
}
