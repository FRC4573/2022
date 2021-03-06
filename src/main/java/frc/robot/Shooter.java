package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;




//Niko was here

public class Shooter {
    //Nada
    //private CANSparkMax motor1 = new CANSparkMax(5, MotorType.kBrushless);
    //Small Motor
    private CANSparkMax motor2 = new CANSparkMax(6, MotorType.kBrushed);
    //Big motor
    private CANSparkMax motor3 = new CANSparkMax(7, MotorType.kBrushless);
    //Elbow
    private CANSparkMax motor4 = new CANSparkMax(8, MotorType.kBrushless);
    //intake
    private CANSparkMax motor5 = new CANSparkMax(9, MotorType.kBrushless);
    //Shooter
    private CANSparkMax motor6 = new CANSparkMax(10,MotorType.kBrushless);

    //Get Motor Functions
    /*public CANSparkMax getMotor1(){
        return this.motor1;
    }*/
    public CANSparkMax getMotor2(){
        return this.motor2;
    }
    public CANSparkMax getMotor3(){
        return this.motor3;
    }
    public CANSparkMax getMotor4(){
        return this.motor4;
    }
    public CANSparkMax getMotor5(){
        return this.motor5;
    }
    public CANSparkMax getMotor6(){
        return this.motor6;
    }   

    //Forward Motor Functions
    /*public void setMotor1Forward(){
        this.motor1.set(1);
    }*/
    public void setMotor2Forward(){
        this.motor2.set(0.9);
    }
    public void setMotor3Forward(){
        this.motor3.set(2);
    }
    public void setMotor4Forward(){
        this.motor4.set(0.55);
    }
    public void setMotor5Forward(){
        this.motor5.set(1);
    }
    public void setMotor6Forward(){
        this.motor6.set(0.75);
    }

    //Reverse Functions
    /*public void setMotorReverse1(){
        this.motor1.set(-1);
    }*/
    public void setMotorReverse2(){
        this.motor2.set(-0.9);
    }   
    public void setMotorReverse3(){
        this.motor3.set(-2);
    }
    public void setMotorReverse4(){
        this.motor4.set(-0.55);
    }
    public void setMotorReverse5(){
       this.motor5.set(-1);
    }
    public void setMotorReverse6(){
        this.motor6.set(-0.75);
    }

    public void autoShooter(){
        this.motor6.set(-0.75);
    }
    public void autoIntake(){
        this.motor5.set(-0.75);
    }

    //Stopping Functions
    /*public void setMotorStop1(){
        this.motor1.set(0.0);
    }*/
    public void setMotorStop2(){
        this.motor2.set(0.0);
    }
    public void setMotorStop3(){
        this.motor3.set(0.0);
    }
    public void setMotorStop4(){
        this.motor4.set(0.0);
    }
    public void setMotorStop5(){
        this.motor5.set(0.0);
    }
    public void setMotorStop6(){
        this.motor6.set(0.0);
    }
}