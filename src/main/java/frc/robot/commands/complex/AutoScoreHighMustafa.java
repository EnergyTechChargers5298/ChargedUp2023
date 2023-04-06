// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.complex;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.basic.ClawGrab;
import frc.robot.commands.basic.ClawRelease;
import frc.robot.commands.closed.ArmSet;
import frc.robot.commands.closed.WristSet;
import frc.robot.commands.closed.ArmSet.ArmPosition;
import frc.robot.commands.closed.WristSet.WristPosition;
import frc.robot.commands.complex.Wrarm.ComboPosition;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoScoreHighMustafa extends SequentialCommandGroup {
  /** Creates a new AutoUno. */
  public AutoScoreHighMustafa() {
    addCommands(
      new WrarmReset(),
      new ClawGrab(),
      new ArmSet(ArmPosition.AUTO),
      new ArmSet(ArmPosition.RETRACTED),
      new ParallelRaceGroup(
        new WristSet(WristPosition.TOP),
        new WaitCommand(0.5)
      ),
      new ParallelRaceGroup(
        new Wrarm(ComboPosition.TOP),
        new WaitCommand(1.5)
      ),
      new WaitCommand(0.5),
      new ClawRelease(),
      new WaitCommand(0.5),
      new ParallelRaceGroup(
        new WaitCommand(1.5),
        new Wrarm(ComboPosition.RETRACTED)
      )
    );
  }
}
