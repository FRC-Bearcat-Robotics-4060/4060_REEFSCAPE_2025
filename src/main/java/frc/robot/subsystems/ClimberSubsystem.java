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

  public ClimberSubsystem()
  {
    // Reset swerveMax to factory defaults
    SparkMaxConfig swerveMaxConfig = new SparkMaxConfig();
    // Set to brake mode
    final int  Amps_Stall = 30;
    final int  Amps_Free = 60;
    swerveMaxConfig.idleMode(IdleMode.kBrake).smartCurrentLimit(Amps_Stall, Amps_Free);
    climberMotor_Main.configure(swerveMaxConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    swerveMaxConfig.follow(Constants.CLIMB_MOTOR_LEFT);
    climberMotor_Follower.configure(swerveMaxConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
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
