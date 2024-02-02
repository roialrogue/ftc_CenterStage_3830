package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "Blue P.2 Auto")
public class P2BlueAuto extends LinearOpMode {
    ElapsedTime runtime = new ElapsedTime();
    Hardware robot = Hardware.getInstance();
    OpenCvCamera webCam;
    FtcDashboard dashboard = FtcDashboard.getInstance();
    boolean isBlue;
    private GPTCamera detector;

    public void runOpMode() {
        robot.init(hardwareMap);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        GPTCamera detector = new GPTCamera(true, telemetry);
        webCam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        webCam.openCameraDevice();
        FtcDashboard.getInstance().startCameraStream(webCam, 0);
        webCam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
        webCam.setPipeline(detector);

        robot.AMotorOutIn.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.AMotorUpDown.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.AMotorOutIn.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.AMotorUpDown.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.ClawRotationServo.setPosition(.642);
        robot.ClawDropServo.setPosition(.5);

        Pose2d BlueP2 = new Pose2d(-33, 61, Math.toRadians(270));
        drive.setPoseEstimate(BlueP2);

        waitForStart();
        webCam.stopStreaming();

        if (GPTCamera.rightSide == true) {
            //Mark right

            Trajectory BlueP2MRT1 = drive.trajectoryBuilder(BlueP2)
                    .lineToLinearHeading(new Pose2d(-34, 36, Math.toRadians(0)))
                    .build();

            Trajectory BlueP2MRT2 = drive.trajectoryBuilder(BlueP2MRT1.end())
                    .forward(10)
                    .build();

            Trajectory BlueP2MRT3 = drive.trajectoryBuilder(BlueP2MRT2.end())
                    .back(10)
                    .build();

            Trajectory BlueP2MRT4 = drive.trajectoryBuilder(BlueP2MRT3.end())
                    .lineToLinearHeading(new Pose2d(-33, 58, Math.toRadians(0)))
                    .build();

            Trajectory BlueP2MRT5 = drive.trajectoryBuilder(BlueP2MRT4.end())
                    .lineToLinearHeading(new Pose2d(5, 58, Math.toRadians(0)))
                    .build();

            Trajectory BlueP2MRT6 = drive.trajectoryBuilder(BlueP2MRT5.end())
                    .splineToConstantHeading(new Vector2d(49, 42), Math.toRadians(0))
                    .build();

            Trajectory BlueP2MRT7 = drive.trajectoryBuilder(BlueP2MRT6.end())
                    .back(10)
                    .build();

            Trajectory BlueP2MRT8 = drive.trajectoryBuilder(BlueP2MRT7.end())
                    .strafeTo(new Vector2d(50, 60))
                    .build();

            drive.followTrajectory(BlueP2MRT1);
            drive.followTrajectory(BlueP2MRT2);
            drive.followTrajectory(BlueP2MRT3);
            drive.followTrajectory(BlueP2MRT4);
            drive.followTrajectory(BlueP2MRT5);
            drive.followTrajectory(BlueP2MRT6);

//            robot.AMotorUpDown.setPower(0.9);
//            robot.AMotorUpDown.setTargetPosition(1000);
//            robot.AMotorUpDown.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            while (opModeIsActive() && Math.abs(robot.AMotorUpDown.getCurrentPosition() - robot.AMotorUpDown.getTargetPosition()) > 5) { }
//            robot.AMotorUpDown.setPower(0);

//            robot.ClawRotationServo.setPosition(0.45);

//            robot.AMotorOutIn.setPower(0.9);
//            robot.AMotorOutIn.setTargetPosition(-1500);
//            robot.AMotorOutIn.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            while (opModeIsActive() && Math.abs(robot.AMotorOutIn.getCurrentPosition() - robot.AMotorOutIn.getTargetPosition()) > 5) { }
//            robot.AMotorOutIn.setPower(0);

//            robot.ClawDropServo.setPosition(0.785);

            drive.followTrajectory(BlueP2MRT7);

//            robot.AMotorOutIn.setPower(0.9);
//            robot.AMotorOutIn.setTargetPosition(-1000);
//            robot.AMotorOutIn.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            while (opModeIsActive() && Math.abs(robot.AMotorOutIn.getCurrentPosition() - robot.AMotorOutIn.getTargetPosition()) > 5) { }
//            robot.AMotorOutIn.setPower(0);

            drive.followTrajectory(BlueP2MRT8);
            drive.turn(Math.toRadians(180));

//            robot.ClawRotationServo.setPosition(0.424);

//            robot.AMotorUpDown.setPower(0.9);
//            robot.AMotorUpDown.setTargetPosition(-300);
//            robot.AMotorUpDown.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            while (opModeIsActive() && Math.abs(robot.AMotorUpDown.getCurrentPosition() - robot.AMotorUpDown.getTargetPosition()) > 5) { }
//            robot.AMotorUpDown.setPower(0);

        } else if (GPTCamera.middleSide == true) {
            //Mark middle

            Trajectory BlueP2MMT1 = drive.trajectoryBuilder(BlueP2)
                    .lineToLinearHeading(new Pose2d(-34, 34, Math.toRadians(270)))
                    .build();

            Trajectory BlueP2MMT2 = drive.trajectoryBuilder(BlueP2MMT1.end())
                    .forward(10)
                    .build();

            Trajectory BlueP2MMT3 = drive.trajectoryBuilder(BlueP2MMT2.end())
                    .back(17)
                    .build();

            Trajectory BlueP2MMT4 = drive.trajectoryBuilder(BlueP2MMT3.end())
                    .lineToLinearHeading(new Pose2d(-33, 58, Math.toRadians(0)))
                    .build();

            Trajectory BlueP2MMT5 = drive.trajectoryBuilder(BlueP2MMT4.end())
                    .lineToLinearHeading(new Pose2d(5, 58, Math.toRadians(0)))
                    .build();

            Trajectory BlueP2MMT6 = drive.trajectoryBuilder(BlueP2MMT5.end())
                    .splineToConstantHeading(new Vector2d(49, 42), Math.toRadians(0))
                    .build();

            Trajectory BlueP2MMT7 = drive.trajectoryBuilder(BlueP2MMT6.end())
                    .back(10)
                    .build();

            Trajectory BlueP2MMT8 = drive.trajectoryBuilder(BlueP2MMT7.end())
                    .strafeTo(new Vector2d(50, 60))
                    .build();

            drive.followTrajectory(BlueP2MMT1);
            drive.followTrajectory(BlueP2MMT2);
            drive.followTrajectory(BlueP2MMT3);
            drive.followTrajectory(BlueP2MMT4);
            drive.followTrajectory(BlueP2MMT5);
            drive.followTrajectory(BlueP2MMT6);

//            robot.AMotorUpDown.setPower(0.9);
//            robot.AMotorUpDown.setTargetPosition(1000);
//            robot.AMotorUpDown.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            while (opModeIsActive() && Math.abs(robot.AMotorUpDown.getCurrentPosition() - robot.AMotorUpDown.getTargetPosition()) > 5) { }
//            robot.AMotorUpDown.setPower(0);

//            robot.ClawRotationServo.setPosition(0.45);

//            robot.AMotorOutIn.setPower(0.9);
//            robot.AMotorOutIn.setTargetPosition(-1500);
//            robot.AMotorOutIn.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            while (opModeIsActive() && Math.abs(robot.AMotorOutIn.getCurrentPosition() - robot.AMotorOutIn.getTargetPosition()) > 5) { }
//            robot.AMotorOutIn.setPower(0);

//            robot.ClawDropServo.setPosition(0.785);

            drive.followTrajectory(BlueP2MMT7);

//            robot.AMotorOutIn.setPower(0.9);
//            robot.AMotorOutIn.setTargetPosition(-1000);
//            robot.AMotorOutIn.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            while (opModeIsActive() && Math.abs(robot.AMotorOutIn.getCurrentPosition() - robot.AMotorOutIn.getTargetPosition()) > 5) { }
//            robot.AMotorOutIn.setPower(0);

            drive.followTrajectory(BlueP2MMT8);
            drive.turn(Math.toRadians(180));

//            robot.ClawRotationServo.setPosition(0.424);

//            robot.AMotorUpDown.setPower(0.9);
//            robot.AMotorUpDown.setTargetPosition(-300);
//            robot.AMotorUpDown.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            while (opModeIsActive() && Math.abs(robot.AMotorUpDown.getCurrentPosition() - robot.AMotorUpDown.getTargetPosition()) > 5) { }
//            robot.AMotorUpDown.setPower(0);

        } else if (GPTCamera.leftSide == true) {
            //Mark left

            Trajectory BlueP2MRT1 = drive.trajectoryBuilder(BlueP2)
                    .lineToLinearHeading(new Pose2d(-34, 32, Math.toRadians(180)))
                    .build();

            Trajectory BlueP2MRT2 = drive.trajectoryBuilder(BlueP2MRT1.end())
                    .forward(10)
                    .build();

            Trajectory BlueP2MRT3 = drive.trajectoryBuilder(BlueP2MRT2.end())
                    .back(10)
                    .build();

            Trajectory BlueP2MRT4 = drive.trajectoryBuilder(BlueP2MRT3.end())
                    .lineToLinearHeading(new Pose2d(-33, 58, Math.toRadians(0)))
                    .build();

            Trajectory BlueP2MRT5 = drive.trajectoryBuilder(BlueP2MRT4.end())
                    .lineToLinearHeading(new Pose2d(5, 58, Math.toRadians(0)))
                    .build();

            Trajectory BlueP2MRT6 = drive.trajectoryBuilder(BlueP2MRT5.end())
                    .splineToConstantHeading(new Vector2d(49, 42), Math.toRadians(0))
                    .build();

            Trajectory BlueP2MRT7 = drive.trajectoryBuilder(BlueP2MRT6.end())
                    .back(10)
                    .build();

            Trajectory BlueP2MRT8 = drive.trajectoryBuilder(BlueP2MRT7.end())
                    .strafeTo(new Vector2d(50, 60))
                    .build();

            drive.followTrajectory(BlueP2MRT1);
            drive.followTrajectory(BlueP2MRT2);
            drive.followTrajectory(BlueP2MRT3);
            drive.followTrajectory(BlueP2MRT4);
            drive.followTrajectory(BlueP2MRT5);
            drive.followTrajectory(BlueP2MRT6);

//            robot.AMotorUpDown.setPower(0.9);
//            robot.AMotorUpDown.setTargetPosition(1000);
//            robot.AMotorUpDown.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            while (opModeIsActive() && Math.abs(robot.AMotorUpDown.getCurrentPosition() - robot.AMotorUpDown.getTargetPosition()) > 5) { }
//            robot.AMotorUpDown.setPower(0);

//            robot.ClawRotationServo.setPosition(0.45);

//            robot.AMotorOutIn.setPower(0.9);
//            robot.AMotorOutIn.setTargetPosition(-1500);
//            robot.AMotorOutIn.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            while (opModeIsActive() && Math.abs(robot.AMotorOutIn.getCurrentPosition() - robot.AMotorOutIn.getTargetPosition()) > 5) { }
//            robot.AMotorOutIn.setPower(0);

//            robot.ClawDropServo.setPosition(0.785);

            drive.followTrajectory(BlueP2MRT7);

//            robot.AMotorOutIn.setPower(0.9);
//            robot.AMotorOutIn.setTargetPosition(-1000);
//            robot.AMotorOutIn.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            while (opModeIsActive() && Math.abs(robot.AMotorOutIn.getCurrentPosition() - robot.AMotorOutIn.getTargetPosition()) > 5) { }
//            robot.AMotorOutIn.setPower(0);

            drive.followTrajectory(BlueP2MRT8);
            drive.turn(Math.toRadians(180));

//            robot.ClawRotationServo.setPosition(0.424);

//            robot.AMotorUpDown.setPower(0.9);
//            robot.AMotorUpDown.setTargetPosition(-300);
//            robot.AMotorUpDown.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            while (opModeIsActive() && Math.abs(robot.AMotorUpDown.getCurrentPosition() - robot.AMotorUpDown.getTargetPosition()) > 5) { }
//            robot.AMotorUpDown.setPower(0);

        } else {
            telemetry.addData("Wait","Wait");
            telemetry.update();
        }
    }
}