package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="__MecanumWheelDrive__", group="LinearOpMode")

/**

 This is the DriveOpMode. This is the OpMode that is used for the driver-controlled portion, and
 is also sometimes used for testing.

 notes:

 The armPosition for the high level of the team shipping hub is -0.588.

 **/

public class __MecanumWheelDrive__ extends LinearOpMode
{

    private DistanceSensor sensorColorRange;
    private Servo servoTest;
    // The line of code below is defining the robot.
    private Hardware3 scout = new Hardware3();
    private double DriveSpeed = 1;
    private double TurnSpeed = 1;
    private double StrafeSpeed = 1;
    private boolean HandIsOpen = false;
    double ArmVerticalPower = 0;
    private LinearOpMode myOpMode;
    //private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode()
    {
        double strafePower = 0;
        double servoPosition = 0;
        double arm2Position = 0;
        double arm2speed = 0;
        if (scout == null)
        {
            scout = new Hardware3();
        }
        scout.init(hardwareMap);
        DriveClassEnergize drive = new DriveClassEnergize(scout, myOpMode);
        telemetry.addData("Status:", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //runtime.reset();

        // run until the end of the match (driver presses STOP)
        double stickDrive = 0;
        double turn = 0;
        double strafe = 0;
        double leftPower = 0;
        double rightPower = 0;

        while (opModeIsActive()) {
            stickDrive = gamepad1.left_stick_y * DriveSpeed;
            turn = this.gamepad1.right_stick_x * TurnSpeed;
            strafe = this.gamepad1.left_stick_x * StrafeSpeed;



            if(this.gamepad2.dpad_up) {
                servoPosition = servoPosition - 0.0007;
                if (servoPosition < -0.9)   servoPosition = -0.9;
            } else if(this.gamepad2.dpad_down) {
                servoPosition = servoPosition + 0.0007;
                if(servoPosition > 0) servoPosition = 0;
            }
            scout.armVerticalServo.setPower(servoPosition);

            if(this.gamepad2.y) {
                scout.armServo2.setPower(-1);
            } else if(this.gamepad2.a) {
                scout.armServo2.setPower(0.3);
            } else {
                scout.armServo2.setPower(-0.065);
            }


            if(this.gamepad2.x) {
                    scout.clawServo.setPosition(0); // NEEDS A VALUE FOR THE POSITION OF THE CLOSED CLAW!
            }

            if(this.gamepad2.b) {
                scout.clawServo.setPosition(0.42); // NEEDS A VALUE FOR THE POSITION OF THE CLOSED CLAW
            }

             leftPower    = -Range.clip(stickDrive - turn, -scout.MAX_DRIVING_POWER, scout.MAX_DRIVING_POWER);
             rightPower   = -Range.clip(stickDrive + turn, -scout.MAX_DRIVING_POWER, scout.MAX_DRIVING_POWER);
             strafePower = Range.clip(-strafe, -scout.MAX_DRIVING_POWER, scout.MAX_DRIVING_POWER);

            scout.leftFrontWheelMotor.setPower(leftPower - strafePower);
            scout.leftRearWheelMotor.setPower(leftPower + strafePower);
            scout.rightFrontWheelMotor.setPower(rightPower + strafePower);
            scout.rightRearWheelMotor.setPower(rightPower - strafePower);

            //drive.StrafeDrive(stickDrive, turn, strafe);
            telemetry.addData("Status", "Running");
            telemetry.addData("Left Power", leftPower);
            telemetry.addData("Right Power", rightPower);
            telemetry.update();

        }
    }
}
