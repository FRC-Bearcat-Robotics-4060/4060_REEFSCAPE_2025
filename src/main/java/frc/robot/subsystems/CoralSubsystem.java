package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;

public class CoralSubsystem extends SubsystemBase
{
  // Add a Swerve Max controller for the NEO motor
  private final SparkMax swerveMax = new SparkMax(Constants.CORAL_MOTOR_CAN_ID, SparkMax.MotorType.kBrushless);

  public CoralSubsystem()
  {
    // Reset swerveMax to factory defaults
    SparkMaxConfig swerveMaxConfig = new SparkMaxConfig();
    swerveMax.configure(swerveMaxConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
  }

  public void eject()
  {
    swerveMax.set(0.5);
  }

  public void stop()
  {
    swerveMax.stopMotor();
  }
}
