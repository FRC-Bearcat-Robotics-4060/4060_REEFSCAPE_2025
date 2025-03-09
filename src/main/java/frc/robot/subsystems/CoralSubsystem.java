package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj.Servo;

public class CoralSubsystem extends SubsystemBase
{
  // Add a Swerve Max controller for the NEO motor
  private final SparkMax swerveMax = new SparkMax(Constants.CORAL_MOTOR_CAN_ID, SparkMax.MotorType.kBrushless);
  private final Servo lockServo = new Servo(Constants.CORAL_LOCK_SERVO_NUMBER);

  private boolean highSpeed = false;

  public CoralSubsystem()
  {
    // Reset swerveMax to factory defaults
    SparkMaxConfig swerveMaxConfig = new SparkMaxConfig();
    // Set to brake mode
    swerveMaxConfig.idleMode(IdleMode.kBrake);
    swerveMax.configure(swerveMaxConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

    // Add the locking servo
    lockDoors();

    // Send to the SmartDashboard
    SmartDashboard.putNumber("CoralMotor", 0.0);
    SmartDashboard.putNumber("CoralPower", 0.15);
    SmartDashboard.getNumber("ClimbCoralPower", 0.15);
    SmartDashboard.putNumber("ClimbCoralPower", 0.35);
  
  }

  public void lockDoors()
  {
    lockServo.set(Constants.CORAL_LOCK_SERVO_POSITION_LOCKED);
  }

  public void unlockDoors()
  {
    lockServo.set(Constants.CORAL_LOCK_SERVO_POSITION_UNLOCKED);
  }

  public void setHighSpeed(boolean highSpeed)
  {
    this.highSpeed = highSpeed;
  }

  public void eject()
  {
    double power;
    if (highSpeed)
    {
      power = SmartDashboard.getNumber("CoralPowerHighSpeed", 0.35);
    }
    else
    {
      power = SmartDashboard.getNumber("CoralPower", 0.15);
    }
     
    swerveMax.set(power );
    SmartDashboard.putNumber("CoralMotor", power);
  }

  public void reverse()
  {
    double power = -0.1;
    swerveMax.set(power);
    SmartDashboard.putNumber("CoralMotor", power);
  }

  public void stop()
  {
    swerveMax.stopMotor();
    SmartDashboard.putNumber("CoralMotor", 0.0);
  }
}
