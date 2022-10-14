package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="autoLeft", group="LinearOpMode")

public class autoLeft extends LinearOpMode{
    private Hardware3 scout = new Hardware3();
    private LinearOpMode bruhOpMode;
    private DriveClassEnergize drive = new DriveClassEnergize(scout, bruhOpMode);


    public void runOpMode() {




}}
