package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**

This is an opmode that defines every function that is related to driving the bot.
Basically every function that is used in some form will be in here.

 notes:



**/

public class DriveClass {

    private Hardware2 scout;
    private LinearOpMode opMode;

    public DriveClass(Hardware2 scout1, LinearOpMode opMode1) {
        scout = scout1;
        opMode = opMode1;
    }

    public void DriveByTime(double power, double seconds){
        ElapsedTime runTime = new ElapsedTime();
        double startTime = runTime.time();

        while(seconds >= (runTime.time() - startTime)){

            scout.leftFrontWheelMotor.setPower(power);
            scout.leftRearWheelMotor.setPower(power);
            scout.rightFrontWheelMotor.setPower(power);
            scout.rightRearWheelMotor.setPower(power);

        }
        halt();
    }


    public void halt(){
        scout.leftFrontWheelMotor.setPower(0);
        scout.leftRearWheelMotor.setPower(0);
        scout.rightFrontWheelMotor.setPower(0);
        scout.rightRearWheelMotor.setPower(0);
    }

    public boolean PrecisionDriveForwardReverse(double power, double inches) {
        // ticks per revolution is 538
        // gear ratio is 1.5 or 2:3
        /**scout.rightFrontWheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        scout.leftFrontWheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        scout.rightRearWheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        scout.leftRearWheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); **/


        double leftFrontDistance = 0;
        double leftRearDistance = 0;
        double rightFrontDistance = 0;
        double rightRearDistance = 0;
        double leftFrontEncoderStart = scout.leftFrontWheelMotor.getCurrentPosition();
        double leftRearEncoderStart = scout.leftRearWheelMotor.getCurrentPosition();
        double rightFrontEncoderStart = scout.rightFrontWheelMotor.getCurrentPosition();
        double rightRearEncoderStart = scout.rightRearWheelMotor.getCurrentPosition();

        double averageEncoderReading = 0;
        while (Math.abs(averageEncoderReading) < Math.abs(scout.TICKS_PER_INCH * inches)){

            scout.leftFrontWheelMotor.setPower(power);
            scout.leftRearWheelMotor.setPower(power);
            scout.rightFrontWheelMotor.setPower(power);
            scout.rightRearWheelMotor.setPower(power);

            leftFrontDistance = scout.leftFrontWheelMotor.getCurrentPosition() - leftFrontEncoderStart;
            leftRearDistance = scout.leftRearWheelMotor.getCurrentPosition() - leftRearEncoderStart;
            rightFrontDistance = scout.rightFrontWheelMotor.getCurrentPosition() - rightFrontEncoderStart;
            rightRearDistance = scout.rightRearWheelMotor.getCurrentPosition() - rightRearEncoderStart;

            averageEncoderReading = (leftFrontDistance + leftRearDistance
                    + rightFrontDistance + rightRearDistance) / 4;

//            opMode.telemetry.addData("Robot is driving forward.", "");
//            opMode.telemetry.update();
        } // end of while loop

        scout.leftFrontWheelMotor.setPower(0);
        scout.leftRearWheelMotor.setPower(0);
        scout.rightFrontWheelMotor.setPower(0);
        scout.rightRearWheelMotor.setPower(0);
        return true;
    }
    public void AutoRotate(int TargetAngle) {

        double currentAngle = GetGyroDegree();
        double comparisonDegree;
        double comparisonDegree2;


        if(TargetAngle > 0){
            while (currentAngle < TargetAngle) {
                scout.leftFrontWheelMotor.setPower(scout.ROTATION_POWER);
                scout.leftRearWheelMotor.setPower(scout.ROTATION_POWER);
                scout.rightFrontWheelMotor.setPower(-scout.ROTATION_POWER);
                scout.rightRearWheelMotor.setPower(-scout.ROTATION_POWER);

                currentAngle = GetGyroDegree();
            }
        } else {
            while (currentAngle > TargetAngle) {
                // Rotate Counter-Clockwise
                scout.leftFrontWheelMotor.setPower(-scout.ROTATION_POWER);
                scout.leftRearWheelMotor.setPower(-scout.ROTATION_POWER);
                scout.rightFrontWheelMotor.setPower(scout.ROTATION_POWER);
                scout.rightRearWheelMotor.setPower(scout.ROTATION_POWER);

                currentAngle = GetGyroDegree();
            }
        }

        halt();
    }

    public void CarouselRight() {
        scout.carouselMotor.setPower(scout.CAROUSEL_POWER);
    }
    public void CarouselLeft() {
        scout.carouselMotor.setPower(-scout.CAROUSEL_POWER);
    }
    public void StopCarousel() {
        scout.carouselMotor.setPower(0);
    }

    public double GetGyroDegree() {
        return -scout.gyro.getAngularOrientation().firstAngle;
    }

    public void Drive(double drive, double turn) {
        double leftPower    = -Range.clip(drive - turn, -scout.MAX_DRIVING_POWER, scout.MAX_DRIVING_POWER);
        double rightPower   = Range.clip(drive + turn, -scout.MAX_DRIVING_POWER, scout.MAX_DRIVING_POWER);


        scout.leftFrontWheelMotor.setPower(leftPower);
        scout.leftRearWheelMotor.setPower(leftPower);
        scout.rightFrontWheelMotor.setPower(-rightPower);
        scout.rightRearWheelMotor.setPower(-rightPower);
    }

    public void StrafeDrive(double drive, double turn, double strafe) {

        double leftPower    = -Range.clip(drive + turn, -scout.MAX_DRIVING_POWER, scout.MAX_DRIVING_POWER);
        double rightPower   = Range.clip(drive - turn, -scout.MAX_DRIVING_POWER, scout.MAX_DRIVING_POWER);
        double strafePower = Range.clip( strafe, -scout.MAX_DRIVING_POWER, scout.MAX_DRIVING_POWER);

        scout.leftFrontWheelMotor.setPower(leftPower - strafePower);
        scout.leftRearWheelMotor.setPower(-rightPower - strafePower);
        scout.rightFrontWheelMotor.setPower(leftPower + strafePower);
        scout.rightRearWheelMotor.setPower(-rightPower + strafePower);
    }

    public void DuckCarousel(double power) {
        scout.carouselMotor.setPower(power);
    }

    public void StopVerticalServo() {
        scout.armVerticalServo.setPower(0);
    }

    public void VerticalServoUp() {
        scout.armVerticalServo.setPower(-1);
    }

    public void VerticalServoDown() {
        scout.armVerticalServo.setPower(1);
    }

    public void ServoElementPosition() {
        scout.armVerticalServo.setPower(-0.25);
    }

    public void Intake(double power){
        scout.intakeServo.setPower(power);
    }

    public void CappingServo(double power) {
        scout.cappingServo.setPower(power);
    }

    public void AutoBlockInShippingHub(int level){
        if(level == 1){
            scout.armVerticalServo.setPower(-0.2);
        } else if(level == 2){
            scout.armVerticalServo.setPower(-0.35);
        } else if(level == 3){
            scout.armVerticalServo.setPower(-0.608);
        }
    }
}
