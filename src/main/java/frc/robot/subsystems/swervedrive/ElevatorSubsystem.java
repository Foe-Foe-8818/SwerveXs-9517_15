package frc.robot.subsystems.swervedrive;
 



import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;



public class ElevatorSubsystem extends SubsystemBase {
    // Define the motor controller and Xbox controller
    private SparkMax elevator;
    private SparkMax leftIntakeMotor;
    private SparkMax rightIntakeMotor;
    private SparkMax source;
    private SparkMax lever;
    private SparkMax ball;

    private XboxController driver;
    private DigitalInput limitSwitch;

    private XboxController xboxController;{

   
        // Initialize the motor controller on CAN ID 1
        elevator = new SparkMax(15, MotorType.kBrushed);
        
        // Initialize the motor controllers on their respective CAN IDs
        leftIntakeMotor = new SparkMax(13, MotorType.kBrushless);
        rightIntakeMotor = new SparkMax(14, MotorType.kBrushless);
        source = new SparkMax(16, MotorType.kBrushless);     
        lever = new SparkMax(17, MotorType.kBrushless);
        ball = new SparkMax(18, MotorType.kBrushless);  
        
        // Initialize the Xbox controller on port 0
        xboxController = new XboxController(1);
        driver = new XboxController(0);

        // digital sensors
        limitSwitch = new DigitalInput(0);
        

        if (driver.getLeftBumperButtonPressed()){
            lever.set(.4);
        }else if (driver.getRightBumperButtonPressed()){
            lever.set(-.4);
        }else {
            lever.set(0);
        }

        if (driver.getRightTriggerAxis() > .1){
            ball.set(.4);
        }else if (driver.getLeftTriggerAxis() > .1){
            ball.set(-.4);
        }else {
            ball.set(0);
        }







        //Cage pivot controls

        // Get the trigger values
        double leftTrigger = xboxController.getLeftTriggerAxis();
        double rightTrigger = xboxController.getRightTriggerAxis();

        // Set the motor speed based on trigger values
        if (rightTrigger > 0.1) {
            // Move motor forward
            elevator.set(rightTrigger * 0.8); // Scale speed down to 50%
        } else if (leftTrigger > 0.1) {
            // Move motor backward
            elevator.set(-leftTrigger * 0.8); // Scale speed down to 50%
        } else {
            // Stop motor
            elevator.set(0);
        }


        // Intake cage controls



         // Check if the left bumper is pressed
         boolean leftBumperPressed = xboxController.getLeftBumperButtonPressed();
         // Check if the right bumper is pressed
         boolean rightBumperPressed = xboxController.getRightBumperButtonPressed();
 
         // Control the motors based on bumper inputs
         if (leftBumperPressed) {
             // Spin motors forward
             leftIntakeMotor.set(.50);  // 50% speed forward
             rightIntakeMotor.set(-.50); // 50% speed forward
         } else if (rightBumperPressed) {
             // Spin motors backward
             leftIntakeMotor.set(-.50);  // 50% speed backward
             rightIntakeMotor.set(.50); // 50% speed backward
         } else {
             // Stop motors
             leftIntakeMotor.set(0);
             rightIntakeMotor.set(0);
         }


         if (xboxController.getXButton()){
            source.set(.4);
         }else if (xboxController.getBButton()){
            source.set(-.4);
         }
         else if (xboxController.getYButton()){
            source.set(0);
         }


         if (limitSwitch.get()){

         }else{

         }

//
    }
    



}