// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;


/** Add your docs here. */
public class ModuleConfig {
    public final int DRIVE_PORT;
    public final int TURN_PORT;

    public final double OFFSET;

    public final String NaMe;

    public ModuleConfig(String name, int dport, int tport, double offset) {
        NaMe = name;
        DRIVE_PORT = dport;
        TURN_PORT = tport;
        OFFSET = offset;
    }
}
