package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class ClimberSubsystem extends SubsystemBase
{
  // Add a Swerve Max controller for the NEO motor
  private final SparkMax climberMotor_Main = new SparkMax(Constants.CLIMB_MOTOR_LEFT, SparkMax.MotorType.kBrushless);
  private final SparkMax climberMotor_Follower = new SparkMax(Constants.CLIMB_MOTOR_RIGHT, SparkMax.MotorType.kBrushless);

  private final SparkMaxConfig swerveMaxConfig_Main = new SparkMaxConfig();
  private final SparkMaxConfig swerveMaxConfig_Follower = new SparkMaxConfig();

  public ClimberSubsystem()
  {
    // Set to brake mode
    final int Amps_Stall = 30;
    final int Amps_Free = 60;

    swerveMaxConfig_Main.idleMode(IdleMode.kBrake)
                        .smartCurrentLimit(Amps_Stall, Amps_Free);
    climberMotor_Main.configure(swerveMaxConfig_Main, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    swerveMaxConfig_Follower.idleMode(IdleMode.kBrake)
                            .smartCurrentLimit(Amps_Stall, Amps_Free)
                            .follow(Constants.CLIMB_MOTOR_LEFT);
    climberMotor_Follower.configure(swerveMaxConfig_Follower, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void setBrake()
  {
    swerveMaxConfig_Main.idleMode(IdleMode.kBrake);
    swerveMaxConfig_Follower.idleMode(IdleMode.kBrake);

    climberMotor_Main.configure(swerveMaxConfig_Main, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    climberMotor_Follower.configure(swerveMaxConfig_Follower, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
  }

  public void setCoast()
  {
    swerveMaxConfig_Main.idleMode(IdleMode.kCoast);
    swerveMaxConfig_Follower.idleMode(IdleMode.kCoast);

    climberMotor_Main.configure(swerveMaxConfig_Main, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    climberMotor_Follower.configure(swerveMaxConfig_Follower, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
  }

  public void start()
  {
    climberMotor_Main.set(Constants.CLIMB_POWER);
  }

  public void stop()
  {
    climberMotor_Main.stopMotor();
  }
}
