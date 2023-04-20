// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.complex;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoNiko extends CommandBase {// extends PIDCommand {

  private double startPos;
  private double currentPos;

  /** Creates a new Engage. */
  public AutoNiko() {
  //   super(
  //       // The controller that the command will use
  //       new PIDController(0.0113, 0, 0),
  //       // This should return the measurement
  //       () -> 0,
  //       // This should return the setpoint (can also be a constant)
  //       () -> 0,
  //       // This uses the output
  //       output -> {
  //         // Use the output here
  //       });
  //   // Use addRequirements() here to declare subsystem dependencies.
  //   // Configure additional PID options by calling `getController` here.
      addRequirements(Drivetrain.getInstance());
   }

  @Override
  public void initialize() {
    super.initialize();
    startPos = Drivetrain.getInstance().getPose2d().getX();
    
  }

  @Override
  public void execute() {
    Drivetrain.getInstance().drive(0.3, 0.0, 0.0);
  
  }


  @Override
  public void end(boolean interrupted) {
    Drivetrain.getInstance().drive(0.0, 0.0, 0.0);
    //Drivetrain.getInstance().
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    currentPos = Drivetrain.getInstance().getPose2d().getX();
    double posDiff = currentPos - startPos;

    return posDiff >= 4;
  }
}
