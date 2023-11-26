import myrmi.Remote;

public interface MatrixCalculator extends Remote {
    public String hello(String name);
    public int[][] add(int[][] a, int[][] b);
    public int[][] subtract(int[][] a, int[][] b);
    public int[][] multiple(int[][] a, int[][] b);
    public int[][] transformation(int[][] a);
}
