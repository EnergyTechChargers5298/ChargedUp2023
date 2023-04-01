// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.complex;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoBalance extends PIDCommand {
  /** Creates a new AutoBalance. */
  public AutoBalance() {
    super(
        // The controller that the command will use
        new PIDController(0.0113, 0, 0),
        // This should return the measurement
        () -> Drivetrain.getInstance().getPitch(),
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          // Use the output here
          Drivetrain.getInstance().drive(output, 0, 0);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Drivetrain.getInstance());
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(2);
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}