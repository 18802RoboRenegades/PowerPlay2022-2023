package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="__TestOpMode__", group="LinearOpMode")

public class __TestOpMode__ extends LinearOpMode
{

    private DistanceSensor sensorColorRange;
    private Servo servoTest;
    // The line of code below is defining the robot.
    private Hardware2 scout = new Hardware2();
    private double DriveSpeed = 1;
    private double TurnSpeed = 1;
    private double ArmSensitivity = 1;
    private double ArmSensitivity2 = 1;
    private boolean HandIsOpen = false;
    private LinearOpMode myOpMode;
    Gamepad.RumbleEffect fortySecRumbleEffect;
    Gamepad.RumbleEffect tenSecRumbleEffect;
    //private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode()
    {
        if (scout == null)
        {
            scout = new Hardware2();
        }
        scout.init(hardwareMap);
        DriveClass drive = new DriveClass(scout, myOpMode);
        telemetry.addData("Status:", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        // Example 1. a)   start by creating a three-pulse rumble sequence: right, LEFT, LEFT
        fortySecRumbleEffect = new Gamepad.RumbleEffect.Builder()
                .addStep(1.0, 1.0, 500)  //  Rumble right motor 100% for 500 mSec
                .addStep(0.0, 0.0, 300)  //  Pause for 300 mSec
                .addStep(1.0, 1.0, 250)  //  Rumble left motor 100% for 250 mSec
                .addStep(0.0, 0.0, 250)  //  Pause for 250 mSec
                .addStep(1.0, 1.0, 250)  //  Rumble left motor 100% for 250 mSec
                .build();
        tenSecRumbleEffect = new Gamepad.RumbleEffect.Builder()
                .addStep(1.0, 0.0, 100)
                .addStep(0.0, 1.0, 100)
                .addStep(1.0, 0.0, 100)
                .addStep(0.0, 1.0, 100)
                .addStep(1.0, 0.0, 100)
                .addStep(0.0, 1.0, 100)
                .addStep(1.0, 0.0, 100)
                .addStep(0.0, 1.0, 100)
                .addStep(1.0, 0.0, 100)
                .addStep(0.0, 1.0, 100)
                .build();

        waitForStart();

        //runtime.reset();

        // run until the end of the match (driver presses STOP)
        double ArmRotationPower = 0;
        double ArmVerticalPower = 0;
        double Arm2Vertical = 0;
        double stickDrive = 0;
        double turn = 0;
        double leftPower = 0;
        double rightPower = 0;

        while (opModeIsActive()) {
            stickDrive = this.gamepad1.left_stick_y * DriveSpeed;
            turn = this.gamepad1.right_stick_x * TurnSpeed;
            //strafe = this.gamepad1.left_stick_x * DriveSpeed;

            if (this.gamepad1.left_bumper) {
                DriveSpeed = .4;
            } else {
                DriveSpeed = 1;
            }

            if (this.gamepad2.right_bumper) {
                drive.Intake(1);
            } else if (this.gamepad2.right_trigger > 0) {
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
                drive.VerticalServoUp();
            } else if(this.gamepad2.dpad_down) {
                drive.VerticalServoDown();
            } else {
                drive.StopVerticalServo();
            }

            if(this.gamepad1.a){
                gamepad1.runRumbleEffect(fortySecRumbleEffect);
            } else if(this.gamepad1.b){
                gamepad1.runRumbleEffect(tenSecRumbleEffect);
            }

            if(this.gamepad2.a){
                gamepad2.runRumbleEffect(fortySecRumbleEffect);
            } else if(this.gamepad2.b){
                gamepad2.runRumbleEffect(tenSecRumbleEffect);
            }

            /**if(getRuntime() <= 80 && getRuntime() >= 82){
                gamepad1.rumble(100);
                gamepad2.rumble(100);
            } else {
                gamepad1.stopRumble();
                gamepad2.stopRumble();
            } **/

            drive.Drive(stickDrive, turn);
            telemetry.addData("Status", "Running");
            telemetry.addData("Left Power", leftPower);
            telemetry.addData("Right Power", rightPower);
            telemetry.update();

        }
    }
}
