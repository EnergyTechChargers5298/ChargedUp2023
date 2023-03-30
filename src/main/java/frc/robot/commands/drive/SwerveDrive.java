// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import java.util.function.Supplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
public class SwerveDrive extends CommandBase {

  private Drivetrain drivetrain;

  private Supplier<Double> xSpeed;
  private Supplier<Double> ySpeed;
  private Supplier<Double> rotSpeed;
  private Supplier<Boolean> noTip;
  private SlewRateLimiter filter;
  private SlewRateLimiter filter2;
  private float ySetpoint;
  private float xSetpoint;
  private double xRate;
  private double yRate;
  private XboxController driver;
  


  // private Supplier<Boolean> fieldsup;

  /** Creates a new SwerveDrive. */
  public SwerveDrive(Supplier<Double> xSpeed, Supplier<Double> ySpeed, Supplier<Double> rotSpeed, Supplier<Boolean> noTip) {
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    this.rotSpeed = rotSpeed;
    this.noTip = noTip;
    ySetpoint = 0;
    xSetpoint = 0;
    driver = new XboxController(0);
    xRate = driver.getLeftX();
    yRate = driver.getLeftY();
    drivetrain = Drivetrain.getInstance();
    filter = new SlewRateLimiter(0.85);
    filter2 = new SlewRateLimiter(0.85);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.drive(0, 0, 0);
    drivetrain.getPitch();
    drivetrain.getRoll();
    drivetrain.resetIMU();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
    if(noTip.get() == true) {
      AntiTip();
    } else{
      drivetrain.drive(filter.calculate(MathUtil.applyDeadband(xSpeed.get(), 0.1)), filter2.calculate(MathUtil.applyDeadband(ySpeed.get(), 0.1)),
      MathUtil.applyDeadband(rotSpeed.get(), 0.1), drivetrain.getFieldCentric());
    }
    // drivetrain.drive(0, 0, 0);
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.drive(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public void AntiTip() {
    if(drivetrain.getPitch() > ySetpoint) {
      xRate = Math.sin(drivetrain.getPitch()) * -1;
      drivetrain.drive(0, yRate, 0);
    }

    if(drivetrain.getRoll() > xSetpoint) {
      yRate = Math.sin(drivetrain.getRoll()) * -1;
      drivetrain.drive(xRate, 0, 0);
    }
  }
}