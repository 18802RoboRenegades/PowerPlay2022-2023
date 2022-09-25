package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class DriveClassEnergize {


    private Hardware3 scout;
    private LinearOpMode opMode;

    public DriveClassEnergize(Hardware3 scout1, LinearOpMode opMode1) {
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

        boolean isClawOpen = true;

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

    public double GetGyroDegree() {
        return -scout.gyro.getAngularOrientation().firstAngle;
    }

    public void StrafeDrive(double drive, double turn, double strafe) {

        double leftPower    = -Range.clip(drive - turn, -scout.MAX_DRIVING_POWER, scout.MAX_DRIVING_POWER);
        double rightPower   = -Range.clip(drive + turn, -scout.MAX_DRIVING_POWER, scout.MAX_DRIVING_POWER);
        double strafePower = Range.clip(-strafe, -scout.MAX_DRIVING_POWER, scout.MAX_DRIVING_POWER);

        scout.leftFrontWheelMotor.setPower(leftPower - strafePower);
        scout.leftRearWheelMotor.setPower(leftPower + strafePower);
        scout.rightFrontWheelMotor.setPower(rightPower + strafePower);
        scout.rightRearWheelMotor.setPower(rightPower - strafePower);
    }

    /**
     *          double leftPower    = -Range.clip(drive + turn, -maxDrivingPower, maxDrivingPower) ;
     *         double rightPower   = Range.clip(drive - turn, -maxDrivingPower, maxDrivingPower) ;
     *         double strafePower = Range.clip( strafe, -maxDrivingPower, maxDrivingPower );
     *
     *         this.LeftFrontWheelMotor.setPower(leftPower - strafePower);
     *         this.LeftRearWheelMotor.setPower(rightPower - strafePower);
     *         this.RightFrontWheelMotor.setPower(leftPower + strafePower);
     *         this.RightRearWheelMotor.setPower(rightPower + strafePower);
     */

    public void Clawwww(boolean putAtrueBooleanHere) {
        if (putAtrueBooleanHere == true) {
            scout.clawServo.setPosition(0); // NEEDS A VALUE FOR THE POSITION OF THE CLOSED CLAW!
        } else if (putAtrueBooleanHere == false){
            scout.clawServo.setPosition(0.42); //NEEDS A VALUE FOR THE POSITION OF THE OPEN CLAW!
        }
    }

}
