package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;

public class CoralSubsystem extends SubsystemBase
{
  // Add a Swerve Max controller for the NEO motor
  private final SparkMax swerveMax = new SparkMax(Constants.CORAL_MOTOR_CAN_ID, SparkMax.MotorType.kBrushless);

  public CoralSubsystem()
  {
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
