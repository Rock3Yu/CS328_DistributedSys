import myrmi.registry.LocateRegistry;
import myrmi.registry.Registry;

import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry();
            String[] regList = registry.list();
            int[][] a = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
            int[][] b = {{3, 3, 3,}, {0, 0, 0}, {5, 5, 5}};
            for (String s : regList) {
                MatrixCalculator cal = (MatrixCalculator) registry.lookup(s);
                System.out.println("------------Connect to stub: " + s + "--------------");
                System.out.println("a: " + Arrays.deepToString(a) + "\nb: " + Arrays.deepToString(b));
                System.out.println("add:       " + Arrays.deepToString(cal.add(a, b)));
                System.out.println("subtract:  " + Arrays.deepToString(cal.subtract(a, b)));
                System.out.println("multiple:  " + Arrays.deepToString(cal.multiple(a, b)));
                System.out.println("transform: " + Arrays.deepToString(cal.transformation(b)));
            }
            System.out.println("--------------Done! :)---------------");
        } catch (Exception e) {
            System.err.println("Client Error");
            e.printStackTrace();
        }
    }
}
