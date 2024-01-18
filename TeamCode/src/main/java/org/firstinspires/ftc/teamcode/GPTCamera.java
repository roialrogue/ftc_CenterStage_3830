package org.firstinspires.ftc.teamcode;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class GPTCamera extends OpenCvPipeline {
    private Mat workingMatrix = new Mat();
    public static boolean leftSide = false;
    public static boolean rightSide = false;
    public static boolean middleSide = false;
    public static boolean nonSide = false;
    public double totalA = 0;
    public double totalB = 0;
    public double totalC = 0;
    public double Atotal = 0;
    public double totalAB = 0;
    public double totalBB = 0;
    public double totalCB = 0;
    public double Btotal = 0;
    public double totalAC = 0;
    public double totalBC = 0;
    public double totalCC = 0;
    public double Ctotal = 0;
    public static int matArowStart = 852;
    public static int matArowEnd = 1278;
    public static int matAcolStart = 0;
    public static int matAcolEnd = 720;


    public static int matBrowStart = 0;
    public static int matBrowEnd = 426;
    public static int matBcolStart = 0;
    public static int matBcolEnd = 720;
    public static int matCrowStart = 426;
    public static int matCrowEnd = 852;
    public static int matCcolStart = 0;
    public static int matCcolEnd = 720;
    boolean isBlue;

    public GPTCamera(boolean isBlue) {
        this.isBlue = isBlue;
    }
    public final Mat processFrame(Mat input) {

        input.copyTo(workingMatrix);

        if (workingMatrix.empty()) {
            return input;
        }

        Mat left = workingMatrix.submat(matArowStart, matArowEnd, matAcolStart, matAcolEnd);
        Mat middle = workingMatrix.submat(matBrowStart, matBrowEnd, matBcolStart, matBcolEnd);
        Mat right = workingMatrix.submat(matCrowStart, matCrowEnd, matCcolStart, matCcolEnd);

        Imgproc.rectangle(workingMatrix, new Rect( matAcolStart, matArowStart, (matArowEnd - matArowStart), (matAcolEnd - matAcolStart)), new Scalar(0, 255, 0));
        Imgproc.rectangle(workingMatrix, new Rect(matBcolStart, matBrowStart, (matBrowEnd - matBrowStart), (matBcolEnd - matBcolStart)), new Scalar(0, 255, 0));
        Imgproc.rectangle(workingMatrix, new Rect(matCcolStart, matCrowStart, (matCrowEnd - matCrowStart), (matCcolEnd - matCcolStart)), new Scalar(0, 255, 0));

        Scalar lowVal, highVal;
        if (isBlue) {
            totalA = Core.sumElems(left).val[2];
            totalA /= left.rows() * left.cols();
            totalB = Core.sumElems(middle).val[2];
            totalB /= middle.rows() * middle.cols();
            totalC = Core.sumElems(right).val[2];
            totalC /= right.rows() * right.cols();
            Atotal = (totalA + totalB + totalC);
        } else {
            totalA = Core.sumElems(left).val[0];
            totalA /= left.rows() * left.cols();
            totalB = Core.sumElems(middle).val[0];
            totalB /= middle.rows() * middle.cols();
            totalC = Core.sumElems(right).val[0];
            totalC /= right.rows() * right.cols();
            Atotal = (totalA + totalB + totalC);
        }

        if ((totalA > totalB) && (totalA > totalC)) {
            leftSide = true;
        } else if ((totalB > totalA) && (totalB > totalC)){
            middleSide = true;
        } else if ((totalC > totalA) && (totalC > totalB)){
            rightSide = true;
        } else {
            nonSide = true;
        }

        return workingMatrix;
    }
}