/*
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
public class CameraInitialization extends OpenCvPipeline {
    public static boolean stoneLeft = false;
    public static boolean stoneMiddle = false;
    public static boolean stoneRight = false;
    Telemetry telemetry;
    Mat mat = new Mat();
    public enum Location {
        LEFT,
        MIDDLE,
        RIGHT,
        NOT_FOUND
    }
    private Location location;
    static final Rect LEFT_ROI = new Rect(
            new Point( 0, 0),
            new Point( 426, 720));
    static final Rect MIDDLE_ROI = new Rect(
            new Point(426,0),
            new Point(852,720));
    static final Rect RIGHT_ROI = new Rect(
            new Point(852,0),
            new Point(1278,720));
    //static double PERCENT_COLOR_THRESHOLD = 0.4;

    boolean isBlue;

    public CameraInitialization(Telemetry t, boolean isBlue) {
        telemetry = t;
        this.isBlue = isBlue;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2HSV);

        */
/*if (isBlue) {
            Scalar lowHSV = new Scalar(100, 100, 100);
            Scalar highHSV = new Scalar(140, 255, 255);
            Core.inRange(mat, lowHSV, highHSV, mat);
        } else {
            Scalar lowHSV = new Scalar(0, 100, 100);
            Scalar highHSV = new Scalar(20, 255, 255);
            Core.inRange(mat, lowHSV, highHSV, mat);
        }*//*


        Scalar lowVal, highVal;
        if(isBlue){
            lowVal = new Scalar(150, 50, 50);
            highVal = new Scalar(290, 255, 255);
        }else{
            lowVal = new Scalar(0, 100, 100);
            highVal = new Scalar(11, 255, 255);
        }
        Mat left = mat.submat(LEFT_ROI);
        Mat middle = mat.submat(MIDDLE_ROI);
        Mat right = mat.submat(RIGHT_ROI);

        double leftValue = Core.sumElems(left).val[0] / LEFT_ROI.area() / 255; // /255
        double middleValue = Core.sumElems(middle).val[0] / MIDDLE_ROI.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / RIGHT_ROI.area() / 255;

        left.release();
        middle.release();
        right.release();

        telemetry.addData("Left raw value", stoneLeft);
        telemetry.addData("Middle raw value", stoneMiddle);
        telemetry.addData("Right raw value", stoneRight);

        double leftper = Math.round(leftValue * 100);
        double middleper = Math.round(middleValue * 100);
        double rightper = Math.round(rightValue * 100);

        if (leftper > rightper && leftper > middleper) {
            stoneLeft = true;
        } else if (middleper > leftper && middleper > rightper){
            stoneMiddle = true;
        } else if (rightper > leftper && rightper > middleper){
            stoneRight = true;
        }
        //boolean stoneLeft = leftValue > PERCENT_COLOR_THRESHOLD;
        //boolean stoneMiddle = middleValue > PERCENT_COLOR_THRESHOLD;
        //boolean stoneRight = rightValue > PERCENT_COLOR_THRESHOLD;

        if (stoneLeft && stoneMiddle && stoneRight) {
            location = Location.NOT_FOUND;
            telemetry.addData("Team Prop Location", "not found");
            telemetry.update();
            //not found
        } else if (stoneLeft) {
            location = location.RIGHT;
            telemetry.addData("Team Prop Location", "Left");
            telemetry.update();
            //right
        } else if (stoneMiddle) {
            location = location.MIDDLE;
            telemetry.addData("Team Prop Location", "middle");
            telemetry.update();
            //middle
        } else if (stoneRight) {
            location = location.LEFT;
            telemetry.addData("Team Prop Location", "Right");
            telemetry.update();
            //left
        }
        telemetry.update();

        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);
        return mat;
        //return input;
    }

    public Location getLocation(){
        return location;
    }

}
*/
