package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
public class MeepMeepRoadRunner {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(52.48291908330528, 52.48291908330528, Math.toRadians(193.2644), Math.toRadians(214.78926857142858), 14.32)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-33, -61, Math.toRadians(90)))

                                .lineToLinearHeading(new Pose2d(-37, -33, Math.toRadians(180)))
                                .forward(9)
                                .back(9)
                .lineToLinearHeading(new Pose2d(-33, -58, Math.toRadians(0)))

                .lineToLinearHeading(new Pose2d(5, -58, Math.toRadians(0)))

                .splineToConstantHeading(new Vector2d(50, -42), Math.toRadians(0))
                                .back(10)



                .strafeTo(new Vector2d(50, -60))

                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}