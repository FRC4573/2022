package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class autonomous {

    Timer timer = new Timer();
    Shooter shooter = new Shooter();
    Drive2 drivetrain = new Drive2();
    
    public void autonomousPeriodic() {



        while (timer.get() <= 2) {

            shooter.setMotorReverse6();// run the intake

            shooter.setMotorReverse5(); // run the shooter

        }



        while (timer.get() > 2 && timer.get() <= 4) {

            shooter.setMotorStop5(); // kill shooter

            shooter.setMotorStop6(); // kill intake

            drivetrain.getDriveTrain().arcadeDrive(0.0, -0.48); // turns robot 180

        }



        while (timer.get() > 4 && timer.get() <= 6) {



            // center the ball

            int Direction = (int) SmartDashboard.getNumber("Direction", -1);

            while (Direction == 0) {

                drivetrain.getDriveTrain().arcadeDrive(0.0, 0.3);

                Direction = (int) SmartDashboard.getNumber("Direction", -1);

                if (timer.get() > 6) {

                    break;

                }

            }



            while (Direction == 1) {

                drivetrain.getDriveTrain().arcadeDrive(0.0, 0.0);

                Direction = (int) SmartDashboard.getNumber("Direction", -1);

                if (timer.get() > 6) {

                    break;

                }

            }



            while (Direction == 2) {

                drivetrain.getDriveTrain().arcadeDrive(0.0, -0.3);

                Direction = (int) SmartDashboard.getNumber("Direction", -1);

                if (timer.get() > 6) {

                    break;

                }

            }

        }



        while (timer.get() > 6 && timer.get() <= 7) { // drive the robot forward for a second so we can get closer and

                                                      // recenter

            drivetrain.getDriveTrain().arcadeDrive(0.4,0.0);

        }



        while (timer.get() > 7 && timer.get() <= 9) { // center the ball again

            int Direction = (int) SmartDashboard.getNumber("Direction", -1);

            while (Direction == 0) {

                drivetrain.getDriveTrain().arcadeDrive(0.0, 0.3);

                Direction = (int) SmartDashboard.getNumber("Direction", -1);

                if (timer.get() > 9) {

                    break;

                }

            }



            while (Direction == 1) {

                drivetrain.getDriveTrain().arcadeDrive(0.0, 0.0);

                Direction = (int) SmartDashboard.getNumber("Direction", -1);

                if (timer.get() > 9) {

                    break;

                }

            }



            while (Direction == 2) {

                drivetrain.getDriveTrain().arcadeDrive(0.0, -0.3);

                Direction = (int) SmartDashboard.getNumber("Direction", -1);

                if (timer.get() > 9) {

                    break;

                }

            }

        }

        while (timer.get() > 9 && timer.get() <= 10.5) {

            shooter.setMotorReverse5();// spool the intake, we dont care about shooter

            drivetrain.getDriveTrain().arcadeDrive(0.4, 0.0); // drive forward and intake ball

        }

        while (timer.get() > 10.5 && timer.get() <= 12.5) { // spin around again

            shooter.setMotorStop5();

            drivetrain.getDriveTrain().arcadeDrive(0.0, -0.48); // turns robot 180

        }

        while (timer.get() > 12.5) {

            drivetrain.getDriveTrain().arcadeDrive(0.0, 0.0);

        }



        // turn around 180 degrees (0.5 is a guess)

        // drivetrain.getDriveTrain().arcadeDrive(0.0, 0.5);



        // Find second ball on floor (leave starting area, extra points)



        // Shoot second ball, maybe repeat

    }

}

