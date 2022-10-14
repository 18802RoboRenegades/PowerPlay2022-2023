package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutoTest", group="LinearOpMode")

public class AutoTest extends LinearOpMode{
    private Hardware3 scout = new Hardware3();
    private LinearOpMode bruhOpMode;
    private DriveClassEnergize drive = new DriveClassEnergize(scout, bruhOpMode);



    public void runOpMode() {

    drive.PrecisionDriveForwardReverse(0.2, 1000);


    }}