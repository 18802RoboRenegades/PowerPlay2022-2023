package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware3 {
    // Drive motors
    public DcMotor leftFrontWheelMotor;
    public DcMotor rightFrontWheelMotor;
    public DcMotor leftRearWheelMotor;
    public DcMotor rightRearWheelMotor;
    //public DcMotor elevatorMotor;
    public Servo clawServo;
    public CRServoImpl armVerticalServo;
    public Servo armServo2;

    public BNO055IMU gyro;
    //public SensorColor colorSensor;

    final public double ROTATION_POWER = 0.4;
    final public double MAX_DRIVING_POWER = 1;
    final public double CAROUSEL_POWER = 0.7;
    final public double TICKS_PER_INCH = 14;

    public void init (HardwareMap hardwareMap)
    {
        // Defining motors and servos
        leftFrontWheelMotor = hardwareMap.get(DcMotor.class, "leftFront");
        rightFrontWheelMotor = hardwareMap.get(DcMotor.class, "rightFront");
        leftRearWheelMotor = hardwareMap.get(DcMotor.class, "leftRear");
        rightRearWheelMotor = hardwareMap.get(DcMotor.class, "rightRear");
        armVerticalServo = hardwareMap.get(CRServoImpl.class, "armVerticalServo");
        armServo2 = hardwareMap.get(Servo.class, "armServo2");
        //elevatorMotor = hardwareMap.get(DcMotor.class, "elevatorMotor");
        clawServo = hardwareMap.get(Servo.class, "clawServo");

        //Defining sensors
        gyro = hardwareMap.get(BNO055IMU.class, "imu 1");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "gyro.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "gyro";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        gyro.initialize(parameters);

        // Setting Motor Power so the motors don't go crazy when initializing
        leftFrontWheelMotor.setPower(0);
        rightFrontWheelMotor.setPower(0);
        leftRearWheelMotor.setPower(0);
        rightRearWheelMotor.setPower(0);

        rightFrontWheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontWheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearWheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRearWheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Motor Mode
        leftFrontWheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontWheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRearWheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRearWheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Motor ZeroPowerBehavior
        leftFrontWheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontWheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRearWheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRearWheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // setting Direction
        leftFrontWheelMotor.setDirection(DcMotor.Direction.REVERSE);
        leftRearWheelMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontWheelMotor.setDirection(DcMotor.Direction.FORWARD);
        rightRearWheelMotor.setDirection(DcMotor.Direction.FORWARD);

        // setting the select servos to position 0
        clawServo.setPosition(0);

    }
}
