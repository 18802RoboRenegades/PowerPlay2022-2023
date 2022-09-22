package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="__DriveOpMode__", group="LinearOpMode")

/**

 This is the DriveOpMode. This is the OpMode that is used for the driver-controlled portion, and
 is also sometimes used for testing.

 notes:

 The armPosition for the high level of the team shipping hub is -0.588.

**/

public class __DriveOpMode__ extends LinearOpMode
{

    private DistanceSensor sensorColorRange;
    private Servo servoTest;
    // The line of code below is defining the robot.
    private Hardware2 scout = new Hardware2();
    private double DriveSpeed = 1;
    private double TurnSpeed = 1;
    private LinearOpMode myOpMode;
    //private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode()
    {

        double servoPosition = 0;
        if (scout == null)
        {
            scout = new Hardware2();
        }
        scout.init(hardwareMap);
        DriveClass drive = new DriveClass(scout, myOpMode);
        telemetry.addData("Status:", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //runtime.reset();

        // run until the end of the match (driver presses STOP)
        double stickDrive = 0;
        double turn = 0;
        double leftPower = 0;
        double rightPower = 0;

        while (opModeIsActive()) {
            stickDrive = this.gamepad1.left_stick_y * DriveSpeed;
            turn = this.gamepad1.right_stick_x * TurnSpeed;

            if (this.gamepad1.left_bumper) {
                DriveSpeed = .4;
            } else {
                DriveSpeed = 1;
            }

            if (this.gamepad2.right_bumper) {
                drive.Intake(1);
            } else if (this.gamepad2.left_bumper) {
                drive.Intake(-1);
            } else {
                drive.Intake(0);
            }

            if (this.gamepad1.dpad_right) {
                drive.CarouselRight();
            } else if (this.gamepad1.dpad_left) {
                drive.CarouselLeft();
            } else {
                drive.StopCarousel();
            }

            if(this.gamepad2.dpad_up) {
                servoPosition = servoPosition - 0.0007;
                if (servoPosition < -0.9)   servoPosition = -0.9;
            } else if(this.gamepad2.dpad_down) {
                servoPosition = servoPosition + 0.0007;
                if(servoPosition > 0) servoPosition = 0;
            } else if(this.gamepad2.b) {
                servoPosition = -0.271;
            }

            if(gamepad2.right_trigger > 0){
                servoPosition += (gamepad2.right_trigger * 0.0005);
                if (servoPosition > 0)   servoPosition = 0;
            } else if(gamepad2.left_trigger > 0) {
                servoPosition -= (gamepad2.left_trigger * 0.0005);
                if (servoPosition < -0.9)   servoPosition = -0.9;
            }
            scout.armVerticalServo.setPower(servoPosition);


            if(this.gamepad2.y) {
                drive.CappingServo(-0.5);
            } else if(this.gamepad2.a) {
                drive.CappingServo(0.5);
            } else {
                drive.CappingServo(0);
            }

            drive.Drive(stickDrive, turn);
            telemetry.addData("arm position = ", servoPosition);
            telemetry.addData("Status", "Running");
            telemetry.addData("Left Power", leftPower);
            telemetry.addData("Right Power", rightPower);
            telemetry.update();

        }
    }
}
