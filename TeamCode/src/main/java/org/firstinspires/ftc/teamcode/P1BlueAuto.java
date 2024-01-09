package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "P.1 Blue Auto")
public class P1BlueAuto extends LinearOpMode{
    boolean isBlue;
    Hardware robot = Hardware.getInstance();
    ElapsedTime runtime = new ElapsedTime();
        public void runOpMode() {
            robot.init(hardwareMap);
            SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
            final boolean[] cameraWorked = {false};
            telemetry.addData("Before Hardware.geInstance","");
            Hardware.getInstance().camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
                @Override
                public void onOpened() {
                    GPTCamera cameraPipeline = new GPTCamera(true);
                    Hardware.getInstance().camera.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
                    Hardware.getInstance().camera.setPipeline(cameraPipeline);
                    telemetry.addData("Webcam has initialized correctly", "");
                    cameraWorked[0] = true;
                }
            //telemetry.addData("Why is this not working","")
                @Override
                public void onError(int errorCode) {
                    telemetry.addData("Camera has broken", "");
                    telemetry.update();
                }
            });
            telemetry.addData("Before if CameraWorked", "");
            if (cameraWorked[0]) {
                Hardware.getInstance().camera.stopStreaming(); //Watch this line
            }

            telemetry.addData("Before Start","");
            waitForStart();

            telemetry.addData("After Start","");
            Trajectory BlueP2M1T1 = drive.trajectoryBuilder(new Pose2d(0,0))
                    .lineToLinearHeading(new Pose2d(10,10, Math.toRadians(180)))
                    .build();

            if (GPTCamera.leftSide == true) {
                telemetry.addData("Found in Auto on the right", "");
                drive.followTrajectory(BlueP2M1T1);
            } else if (GPTCamera.middleSide == true) {
                telemetry.addData("Found in Auto on the middle", "");
                drive.followTrajectory(BlueP2M1T1);
            } else if (GPTCamera.rightSide == true) {
                telemetry.addData("Found in Auto on the left", "");
                drive.followTrajectory(BlueP2M1T1);
            } else if (GPTCamera.nonSide == true) {
                telemetry.addData("Did not find in Auto","");
                telemetry.update();
                drive.followTrajectory(BlueP2M1T1);
            }
        }

    }