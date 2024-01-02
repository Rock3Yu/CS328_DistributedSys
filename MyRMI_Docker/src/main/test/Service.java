import myrmi.Remote;
import myrmi.registry.LocateRegistry;
import myrmi.registry.Registry;
import myrmi.server.UnicastRemoteObject;

public class Service {
    public static void main(String[] args) {
        try{
            Registry registry = LocateRegistry.createRegistry();
            Remote calculator_1 = new MatrixCalculatorImpl();
            Remote calculator_2 = new AdvancedMatrixCalculator();
            Remote stub_1 = UnicastRemoteObject.exportObject(calculator_1);
            Remote stub_2 = UnicastRemoteObject.exportObject(calculator_2);
            registry.bind("random_name", stub_1);
            registry.bind("fast_calculator", stub_2);
//            registry.bind("stub_2", null);  // test, will cause duplication error
        } catch (Exception e) {
            System.err.println("Server Error");
            e.printStackTrace();
        }
    }
}
