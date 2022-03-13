/*----------------------------------------------------------------------------*/
/* Copyright (c) 2021 - 2022 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import edu.wpi.cscore.MjpegServer;
//import edu.wpi.cscore.UsbCamera;
//import edu.wpi.cscore.VideoSource;
//import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PWMSparkMax;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Relay.Direction;
//import edu.wpi.first.wpilibj.Spark;
import org.opencv.core.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList.DropLocation;

//***********************************************************
//A.R
//1/18/2022

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

//import edu.wpi.cscore.CvSink;
//import edu.wpi.cscore.CvSource;
//import edu.wpi.cscore.UsbCamera;
//import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.cscore.MjpegServer;

 //**************************************************************************\\
 // The VM is configured to automatically run this class, and to call the    \\
 // functions corresponding to each mode, as described in the TimedRobot     \\
 // documentation. If you change the name of this class or the package after \\
 // creating this project, you must also update the build.gradle file in the \\
 // project.                                                                 \\
 //**************************************************************************\\
public class Robot extends TimedRobot {
  Joystick drive_stick;
  Joystick control_stick;

  double m_deadZone;
  double m_driveMotorSpeed;
  double m_driveTurnSpeed;
  double displayCtr;
  static final int IMG_WIDTH = 320;
  static final int IMG_HEIGHT = 240;

//////////////////////////BUTTON MAPPINGs\\\\\\\\\\\\\\\\\\\\\\\\\
  
 // Control Stick \\
 /*  sample for button mappings
  static final int BTNSHOOTERBACK = 11;  // Reverse Shooter
    */                    
  // Drive Stick \\
  //////////////////////////BUTTON MAPPINGs\\\\\\\\\\\\\\\\\\\\\\\\\
  static boolean commandRan = false;
  Joystick joystick = new Joystick(0);
  Joystick joystick1 = new Joystick(1);

  //ADXRS450_Gyro gyro;

  //Talon motor1 = new Talon(0);
  //Talon motor2 = new Talon(1);
  //Talon motor3 = new Talon(2);

    //////////////////////////Motor Controllers\\\\\\\\\\\\\\\\\\\\\\\\\
  //This is the drive train that uses talons, comment out whichever is not being used
  DriveTalon drivetrain = new DriveTalon();
  //this  is the driveTrain that uses VictorSpx
  //Drive2 drivetrain = new Drive2();
  
  
  Shooter shooter = new Shooter();

    //////////////////////////Motor Controllers\\\\\\\\\\\\\\\\\\\\\\\\\

  Timer timer = new Timer();

  
  
  //****************************************************************************\\
  // This function is run when the robot is first started up and should be used \\
  // for any initialization code.                                               \\
  //****************************************************************************\\
  
  
  @Override
  public void robotInit() {
    
    System.out.println("Robot Init: ");

    m_deadZone = 0.3;
    m_driveMotorSpeed = 1.5;
    m_driveTurnSpeed = 1;
    displayCtr = 0;
    //////////////////////////Speed Settings\\\\\\\\\\\\\\\\\\\\\\\\\



    //////////////////////////Speed Settings\\\\\\\\\\\\\\\\\\\\\\\\\
    
    
  drive_stick = new Joystick(0);
  control_stick = new Joystick(1);

    UsbCamera camera0 = CameraServer.startAutomaticCapture();
   // CameraServer camera = CameraServer.startAutomaticCapture();
    camera0.setResolution(480, 480);
    camera0.setFPS(15);

  
  //gyro = new ADXRS450_Gyro(); // Digital Gyro on SPI interface of RoboRIO
  //gyro.calibrate(); // Calibrate Gyro on start
  //Shuffleboard.getTab("Gyroscope").add(gyro)
  
  }
  


  //*********************************************************************************\\
  // This function is called every robot packet, no matter the mode. Use this for    \\
  // items like diagnostics that you want ran during disabled, autonomous,           \\
  // teleoperated and test.                                                          \\
  //                                                                                 \\                                                                            \\
  // This runs after the mode specific periodic functions, but before LiveWindow     \\
  // and SmartDashboard integrated updating.                                         \\
  //*********************************************************************************\\
  

  @Override
  public void autonomousInit() {
    System.out.println("StartAutoInit");
    timer.start();
    System.out.println("EndAutoInit");
    
    
    
    System.out.println("Autonomous");
  }

 
  @Override
  public void autonomousPeriodic() {
    
    while(timer.get() <= 2){
      shooter.autoIntake();
      shooter.autoShooter();
    }

    while(timer.get() <= 8){
      shooter.setMotorStop5();
      shooter.setMotorStop6();
      drivetrain.getDriveTrain().arcadeDrive(0.0, 0.55);
    }
    drivetrain.getDriveTrain().arcadeDrive(0.0, 0.0);
   
      while(timer.get() <= 12){
        shooter.setMotorReverse5();
        drivetrain.getDriveTrain().arcadeDrive(0.60, 0.0);
      }
    }
  /*
  //code for autonomous period, timer and while recommended \\

 
  
  // 3 step process

        //Shoot prelaoded ball into hoop(place robot so it goes in without moving)
    while(timer.get() <= 2){
      //this turns the robot 180
       
      //spin shooter motor
        //shooter.setMotorReverse6();
        shooter.setMotorReverse5();
    }
    while(timer.get()>2 && timer.get()<=4 ){
        drivetrain.getDriveTrain().arcadeDrive(0.0,-0.45); 
        
    }

    //drivetrain.getDriveTrain().arcadeDrive(0.0,0.0);

    //turn the robot completely
    while(timer.get()>4 && timer.get()<=6){
        shooter.setMotorStop5();
        shooter.setMotorStop6();
      //center the ball
      int Direction = (int)SmartDashboard.getNumber("Direction", -1);

      while(Direction==0){
        drivetrain.getDriveTrain().arcadeDrive(0.0,0.35);
        Direction = (int)SmartDashboard.getNumber("Direction", -1);
        if(timer.get()>6){
          break;
        }
      }

      while(Direction == 1){
      drivetrain.getDriveTrain().arcadeDrive(0.0, 0.0);
      Direction = (int)SmartDashboard.getNumber("Direction", -1);
      if(timer.get()>6){
        break;
      }
      }

      while(Direction == 2){
      drivetrain.getDriveTrain().arcadeDrive(0.0,-0.35);
      Direction = (int)SmartDashboard.getNumber("Direction", -1);
        if(timer.get()>6){
          break;
        }
      }
     
    }

    //drivetrain.getDriveTrain().arcadeDrive(0.0,0.0);

    //use the camera to align with the ball
    while(timer.get() > 6 && timer.get()<8){
      shooter.setMotorStop5();
      shooter.setMotorStop6();
      int Direction3 = (int)SmartDashboard.getNumber("y",-1);

      while(Direction3==0){
        drivetrain.getDriveTrain().arcadeDrive(0.4,0.0);
        Direction3 = (int)SmartDashboard.getNumber("y", -1);

        if(timer.get()>8){
          break;
          }

        } 

      while(Direction3 == 1){
      drivetrain.getDriveTrain().arcadeDrive(0.0, 0.0);
      Direction3 = (int)SmartDashboard.getNumber("y", -1);
      if(timer.get()>8){
        break;
        }
      }

      while(Direction3 == 2){
      drivetrain.getDriveTrain().arcadeDrive(0.0, 0.0);
      Direction3 = (int)SmartDashboard.getNumber("y", -1);
      if(timer.get()>8){
        break;
        }
      }
      shooter.setMotorReverse4();
      shooter.setMotorReverse5();
    }
    //shooter.setMotorStop4();
    //shooter.setMotorStop5();
    //drive towards the ball and intake the ball
    /*
    while(timer.get()<10){

      drivetrain.getDriveTrain().arcadeDrive(0.0, 0.0);

      int Direction3 = (int)SmartDashboard.getNumber("y", -1);

      while(Direction3==0){
        drivetrain.getDriveTrain().arcadeDrive(-0.4,0.0);
        Direction3 = (int)SmartDashboard.getNumber("y", -1);        
      }

      while(Direction3 == 1){
      drivetrain.getDriveTrain().arcadeDrive(0.0, 0.0);
      Direction3 = (int)SmartDashboard.getNumber("y", -1);
      }

      while(Direction3 == 2){
      drivetrain.getDriveTrain().arcadeDrive(0.0, 0.0);
      Direction3 = (int)SmartDashboard.getNumber("y", -1);
      }

    }
    
    //intake the ball 
    while(timer.get()<12){
      
    }
    //turn around
    while(timer.get()<15){
      drivetrain.getDriveTrain().arcadeDrive(0.0,-0.48);
      if(timer.get()>=15){
        drivetrain.getDriveTrain().arcadeDrive(0.0, 0.0);
        break;
      }
    }
    //turn around 180 degrees (0.5 is a guess)
    //drivetrain.getDriveTrain().arcadeDrive(0.0, 0.5);


        //Find second ball on floor (leave starting area, extra points)

        //Shoot second ball, maybe repeat
        */
    

  //********************************************************\\
  // This function is called at the start of Teloeop.       \\
  //********************************************************\\
  @Override
  public void teleopInit() {
    drivetrain.getDriveTrain().arcadeDrive(0.0,0.0);
    System.out.println("Teleop");
  }

  //*******************************************************************\\
  // This function is called periodically during operator control.     \\
  //*******************************************************************\\
  @Override
  public void teleopPeriodic() {

    // Get Drive Joystick input for arcade driving
    //shooter.setMotor1Forward();
    double X = getJoystickValue(drive_stick, 1) * m_driveMotorSpeed;
    double Z = getJoystickValue(drive_stick, 2) * m_driveTurnSpeed;

    //System.out.println(Z + " " + X);
    
    drivetrain.getDriveTrain().arcadeDrive(-X, Z, true); // Drive the robot
    

    //System.out.println(Direction);
    /*
    if(joystick.getRawButtonPressed(4)==true){

          while(Direction==0){
            drivetrain.getDriveTrain().arcadeDrive(0.0,0.3);
            Direction = (int)SmartDashboard.getNumber("Direction", -1);
          }

          while(Direction == 1){
          drivetrain.getDriveTrain().arcadeDrive(0.0, 0.0);
          Direction = (int)SmartDashboard.getNumber("Direction", -1);
          }

          while(Direction == 2){
          drivetrain.getDriveTrain().arcadeDrive(0.0,-0.3);
          Direction = (int)SmartDashboard.getNumber("Direction", -1);
          }

      }

      */
      /* kmmmmmmm
      if(joystick.getRawButtonPressed(6)==true){

        while(Direction3==0){
          drivetrain.getDriveTrain().arcadeDrive(-0.4,0.0);
          Direction3 = (int)SmartDashboard.getNumber("y", -1);

          if(joystick.getRawButtonReleased(6) == true){
            break;
          }
        }

        while(Direction3 == 1){
        drivetrain.getDriveTrain().arcadeDrive(0.0, 0.0);
        Direction3 = (int)SmartDashboard.getNumber("y", -1);
        }

        while(Direction3 == 2){
        drivetrain.getDriveTrain().arcadeDrive(0.0, 0.0);
        Direction3 = (int)SmartDashboard.getNumber("y", -1);
        }
      }

    if(joystick.getRawButtonReleased(6)== true){
          drivetrain.getDriveTrain().arcadeDrive(0.0,0.0);
        }
        */
      /*
      if(joystick.getRawButtonReleased(3)== true){
        drivetrain.getDriveTrain().arcadeDrive(0.0,0.0);
      }
      if(joystick.getRawButtonPressed(4)==true){
        drivetrain.getDriveTrain().arcadeDrive(-1, 0.0);

      }
      if(joystick.getRawButtonReleased(4)==true){
        drivetrain.getDriveTrain().arcadeDrive(0.0, 0.0);

      }
      */

    /**
     * 
     * 
     * Timer & stuff
     * 
     */

    Timer timer = new Timer();
  
   /* 
    if(joystick.getRawButtonPressed(7)){

      timer.start();

      while(!timer.hasPeriodPassed(3000.0)){
        shooter.setMotor3Forward();
      }
        shooter.setMotorStop3();
      while(!timer.hasPeriodPassed(3500)){
        drivetrain.getDriveTrain().arcadeDrive(0.4, 0, true);
      }
      while(!timer.hasPeriodPassed(5500)){
        shooter.setMotorReverse3();
      }
        shooter.setMotorStop3();
      while(!timer.hasPeriodPassed(12000)){
        shooter.setMotor2Forward();
      }
        shooter.setMotorStop2();

        timer.stop();
    }

    */
    /*if(joystick.getRawButtonPressed(8)){

      timer.start();

      while(!timer.hasPeriodPassed(6000)){
        shooter.setMotorReverse2();
      }
        shooter.setMotorStop2();
      while(!timer.hasPeriodPassed(6800)){
        shooter.setMotor3Forward();
      }
        shooter.setMotorStop3();
      while(!timer.hasPeriodPassed(7300)){
        drivetrain.getDriveTrain().arcadeDrive(-0.4, 0, true);
      }
      while(!timer.hasPeriodPassed(9300)){
        shooter.setMotorReverse3();
      }
        shooter.setMotorStop3();

        timer.stop();
    } */
/**
 * 
 * End of Timer & Stuff
 * 
 */
    
    if(joystick.getRawButtonPressed(9)==true){
      shooter.setMotor4Forward();
    }  
    if(joystick.getRawButtonReleased(9)== true){
      shooter.setMotorStop4();
    }
    if(joystick.getRawButtonPressed(10)==true){
      shooter.setMotorReverse4();
    }
    if(joystick.getRawButtonReleased(10)== true){
      shooter.setMotorStop4();
    }
    if(joystick1.getRawButtonPressed(6)==true){ 
      shooter.setMotor2Forward();
    }
    if(joystick1.getRawButtonReleased(6)== true){
      shooter.setMotorStop2();
    }
    if(joystick1.getRawButtonPressed(7)==true){
      shooter.setMotorReverse2();
    }
    if(joystick1.getRawButtonReleased(7)== true){
      shooter.setMotorStop2();
    }
    if(joystick1.getRawButtonPressed(10)==true){
      shooter.setMotor3Forward();
    }
    if(joystick1.getRawButtonReleased(10)== true){
      shooter.setMotorStop3();
    }
    if(joystick1.getRawButtonPressed(11)==true){
      shooter.setMotorReverse3();
    }
    if(joystick1.getRawButtonReleased(11)== true){
      shooter.setMotorStop3();
    }
    
    if(joystick1.getRawButtonPressed(3)== true){
      shooter.setMotor6Forward();
    }
    if(joystick1.getRawButtonReleased(3)== true){
      shooter.setMotorStop6();
    }
    
    if(joystick1.getRawButtonPressed(1) == true){
      shooter.setMotorReverse6();
    }
    if(joystick1.getRawButtonReleased(1) == true){
      shooter.setMotorStop6();
    }
    
    if(joystick1.getRawButtonPressed(4)==true){
      shooter.setMotor5Forward();
    }
    if(joystick1.getRawButtonReleased(4)== true){
      shooter.setMotorStop5();
    }
    if(joystick1.getRawButtonPressed(5)==true){
      shooter.setMotorReverse5();
    }
    if(joystick1.getRawButtonReleased(5)== true){
      shooter.setMotorStop5();
    }
  }


//gearbox test\\
   /* if(joystick.getRawButtonPressed(3)==true){
      gearbox.setSpeed(0.5);}*/
   

  @Override
  public void robotPeriodic() {
    
  }
  

  @Override
  public void testPeriodic() {
    
  }

  //*******************************************************************\\
  //This function is used to read joystick & eliminate deadzone issues \\
  //*******************************************************************\\

  public double getJoystickValue(Joystick joystick, int iKey) {
    double dVal = joystick.getRawAxis(iKey);
    if (Math.abs(dVal) < m_deadZone) {
      return 0;
    } else {
      return dVal;
    }
  }
}
  
  