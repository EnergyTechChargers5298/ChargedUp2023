// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.complex;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.basic.ArmReset;
import frc.robot.commands.basic.ClawRelease;
import frc.robot.commands.basic.IntakeEat;
import frc.robot.commands.basic.WristReset;
import frc.robot.commands.complex.Wrarm.ComboPosition;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoDos extends SequentialCommandGroup {
  /** Creates a new AutoUno. */
  public AutoDos() {

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ArmReset(),
      new WristReset(),
      new ParallelRaceGroup(
        new Wrarm(ComboPosition.TOP),
        new WaitCommand(3)
      ),
      new ClawRelease(),
      new RobotContainer().PathDos,
      new ParallelRaceGroup(
        new IntakeEat(),
        new WaitCommand(2)
      )
    );
  }
}
