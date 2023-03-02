package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.SwerveConstants;
import frc.robot.utils.ModuleConfig;

public class SwerveModule {
    // MOTORS!!!
    private final CANSparkMax driveMotor;
    private final CANSparkMax turnMotor;
    
    // ENCODERS!!!
    private final RelativeEncoder driveEncoder;
    private final AbsoluteEncoder turnEncoder;

    public SwerveModuleState currentState;

    // PID controller for turning
    public final SparkMaxPIDController turnController;

    public final ModuleConfig config;


    public SwerveModule(ModuleConfig config) {
        // initializing the drive and turn motors
        driveMotor = new CANSparkMax(config.DRIVE_PORT, MotorType.kBrushless);
        turnMotor = new CANSparkMax(config.TURN_PORT, MotorType.kBrushless);

        // getting the drive and turn encoders
        driveEncoder = driveMotor.getEncoder();
        turnEncoder = turnMotor.getAbsoluteEncoder(Type.kDutyCycle);

        // converting the drive factors to meters and the turn factors to radians
        driveEncoder.setVelocityConversionFactor(((Math.PI * SwerveConstants.WHEEL_DIAMETER) / SwerveConstants.GEER_RATTIOLI) / 60); 
        driveEncoder.setPositionConversionFactor((Math.PI * SwerveConstants.WHEEL_DIAMETER) / SwerveConstants.GEER_RATTIOLI);

        turnEncoder.setVelocityConversionFactor((Math.PI * 2) / 60);
        turnEncoder.setPositionConversionFactor(Math.PI * 2);

        turnEncoder.setInverted(SwerveConstants.TURN_INVERSION);

        //turnEncoder.setZeroOffset(angleOffset);

        currentState = new SwerveModuleState();

        turnController = SwerveConstants.TURN_PID.getConfiguredController(turnMotor, turnEncoder);
        
        turnMotor.burnFlash();

        this.config = config;
    }

    /**
     * Gets the state of the swerve module
     * @author Aiden Sing
     * @return the velocity
     */ 
    public SwerveModuleState getState() {
        return new SwerveModuleState(driveEncoder.getVelocity(), new Rotation2d(turnEncoder.getPosition()));
    }

    /**
     * Gets the position of the swerve module
     * @author Aiden Sing
     * @return the position
     */ 
    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(driveEncoder.getPosition(), new Rotation2d(turnEncoder.getPosition()));
    }

    /**
     * Moves the swerve module
     * @author Aiden Sing
     * @param desiredState Where the module should go
     */
    public void setState(SwerveModuleState desiredState) {
        // updating the desired state using the angle offset
        //desiredState.angle.plus(Rotation2d.fromRadians(angleOffset));

        // optimizing the state of the angle
        SwerveModuleState optimizedState = SwerveModuleState.optimize(desiredState, getState().angle);

        // running the optimized state
        driveMotor.set(optimizedState.speedMetersPerSecond / SwerveConstants.TOP_SPEED);

        if(Math.abs(desiredState.angle.minus(currentState.angle).getRadians()) > SwerveConstants.ANGLE_THRESHOLD) {
            turnController.setReference(optimizedState.angle.getRadians(), ControlType.kPosition);
        }

        currentState = getState();
    }

    public void updateTelemetry() {
        SmartDashboard.putNumber(config.NaMe + " Angle Degrees", getPosition().angle.getDegrees());
        SmartDashboard.putNumber(config.NaMe + " Angle Radians", getPosition().angle.getRadians());

        SmartDashboard.putNumber(config.NaMe + " Drive Position", getPosition().distanceMeters);
    }

    /**
     * Resets the drive encoder
     * @author Aiden Sing
     */
    public void resetEncoder() {
        driveEncoder.setPosition(0.0);
    }
}
