package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Auto Test", group="LinearOpMode")

public class AutoTest extends LinearOpMode {
    private Hardware3 scout = new Hardware3();
    private LinearOpMode bruhOpMode;
    private DriveClassEnergize drive = new DriveClassEnergize(scout, bruhOpMode);

    @Override

    public void runOpMode() {
        scout.init(hardwareMap);
        waitForStart();
       drive.PrecisionStrafe(0.3, 10);

    }}