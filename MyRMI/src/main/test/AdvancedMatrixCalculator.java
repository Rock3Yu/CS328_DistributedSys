import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Arrays;

public class AdvancedMatrixCalculator implements MatrixCalculator {

    public String hello(String name) {
        return null;
    }

    @Override
    public int[][] add(int[][] a, int[][] b) {
        RealMatrix matrixA = new Array2DRowRealMatrix(getDoubleArr(a));
        RealMatrix matrixB = new Array2DRowRealMatrix(getDoubleArr(b));
        RealMatrix resultMatrix = matrixA.add(matrixB);

        return getIntArr(resultMatrix.getData());
    }

    @Override
    public int[][] subtract(int[][] a, int[][] b) {
        RealMatrix matrixA = new Array2DRowRealMatrix(getDoubleArr(a));
        RealMatrix matrixB = new Array2DRowRealMatrix(getDoubleArr(a));
        RealMatrix resultMatrix = matrixA.subtract(matrixB);

        return getIntArr(resultMatrix.getData());
    }

    @Override
    public int[][] multiple(int[][] a, int[][] b) {
        RealMatrix matrixA = new Array2DRowRealMatrix(getDoubleArr(a));
        RealMatrix matrixB = new Array2DRowRealMatrix(getDoubleArr(b));
        RealMatrix resultMatrix = matrixA.multiply(matrixB);

        return getIntArr(resultMatrix.getData());
    }

    @Override
    public int[][] transformation(int[][] a) {
        RealMatrix matrixA = new Array2DRowRealMatrix(getDoubleArr(a));
        RealMatrix resultMatrix = matrixA.transpose();

        return getIntArr(resultMatrix.getData());
    }

    private int[][] getIntArr(double[][] a) {
        return Arrays.stream(a)
                .map(row -> Arrays.stream(row)
                        .mapToInt(value -> (int) value)
                        .toArray())
                .toArray(int[][]::new);
    }

    private double[][] getDoubleArr(int[][] a) {
        return Arrays.stream(a)
                .map(row -> Arrays.stream(row)
                        .mapToDouble(value -> (double) value)
                        .toArray())
                .toArray(double[][]::new);
    }
}
