// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.basic.ArmReach;
import frc.robot.commands.basic.ArmRetract;
import frc.robot.commands.basic.ClawGrab;
import frc.robot.commands.basic.ClawHigh;
import frc.robot.commands.basic.ClawLow;
import frc.robot.commands.basic.ClawRelease;
import frc.robot.commands.basic.FrisbeeClockwise;
import frc.robot.commands.basic.FrisbeeCounterClockwise;
import frc.robot.commands.basic.IntakeChomp;
import frc.robot.commands.basic.IntakeEat;
import frc.robot.commands.basic.IntakeLift;
import frc.robot.commands.basic.IntakeSpit;
import frc.robot.commands.drive.SwerveDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.utils.TriggerButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
 public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private static final XboxController driveController = new XboxController(Ports.DRIVER);
  private static final XboxController operatorController = new XboxController(Ports.OPERATOR);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    Drivetrain.getInstance().setDefaultCommand(new SwerveDrive(
      () -> -driveController.getRawAxis(1),
      () -> driveController.getRawAxis(0),
      () -> driveController.getRawAxis(4)
    ));

    new JoystickButton(driveController,Button.kB.value).whileTrue(new IntakeEat());
    new JoystickButton(driveController,Button.kA.value).whileTrue(new IntakeSpit());
    new JoystickButton(driveController,Button.kX.value).whileTrue(new IntakeChomp());
    new JoystickButton(driveController,Button.kY.value).whileTrue(new IntakeLift());
    

    new JoystickButton(operatorController,Button.kX.value).whileTrue(new ClawGrab());
    new JoystickButton(operatorController,Button.kB.value).whileTrue(new ClawRelease());
    new JoystickButton(operatorController,Button.kY.value).whileTrue(new ArmReach());
    new JoystickButton(operatorController,Button.kA.value).whileTrue(new ArmRetract());
    
    new JoystickButton(operatorController,Button.kRightBumper.value).whileTrue(new FrisbeeClockwise());
    new JoystickButton(operatorController,Button.kLeftBumper.value).whileTrue(new FrisbeeCounterClockwise());

    new TriggerButton(operatorController, XboxController.Axis.kLeftTrigger).whileTrue(new ClawLow());
    new TriggerButton(operatorController, XboxController.Axis.kRightTrigger).whileTrue(new ClawHigh());
  
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command m_autonomousCommand() {
    return null;
  }
}
