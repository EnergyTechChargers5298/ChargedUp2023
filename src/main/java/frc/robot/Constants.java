// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;
import frc.robot.utils.ModuleConfig;
import frc.robot.utils.PIDF;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class SwerveConstants {
    public static final ModuleConfig SWERVE_FL = new ModuleConfig("FL", Ports.SWERVE_DRIVE_FL, Ports.SWERVE_TURN_FL, 1.158);
    public static final ModuleConfig SWERVE_FR = new ModuleConfig("FR", Ports.SWERVE_DRIVE_FR, Ports.SWERVE_TURN_FR, 5.797);
    public static final ModuleConfig SWERVE_BL = new ModuleConfig("BL", Ports.SWERVE_DRIVE_BL, Ports.SWERVE_TURN_BL, 0.567);
    public static final ModuleConfig SWERVE_BR = new ModuleConfig("BR", Ports.SWERVE_DRIVE_BR, Ports.SWERVE_TURN_BR, 3.213);

    public static final PIDF TURN_PID = new PIDF(0.13, 0, 2 * Math.PI, -1, 1, false);
    public static final double ANGLE_THRESHOLD = Units.degreesToRadians(2);
    public static final  boolean TURN_INVERSION = true;
    public static final double TOP_ANGULAR_SPEED = 2 * 2 * Math.PI;

    public static final double TOP_SPEED = Units.feetToMeters(9.6);
    public static final double GEER_RATTIOLI = 6.55;

    public static final double TRACK_WIDTH = Units.inchesToMeters(23);
    public static final double WHEEL_BASE = Units.inchesToMeters(23);
    public static final double WHEEL_DIAMETER = Units.inchesToMeters(4);
  }
  
  public static class InConstants {
    public static final boolean INTAKE_INVERTED = true;
    public static final double INTAKE_SPEED = 1.0;

    public static final boolean FRISBEE_INVERTED = false;
    public static final double FRISBEE_SPEED = 1.0;
  }
  
  public static class OutConstants {
    public static final boolean ARM_INVERTED = false;
    public static final double ARM_SPEED = 1.0;

    public static final boolean WRIST_INVERTED = false;
    public static final double WRIST_SPEED = 0.5;
  }
}