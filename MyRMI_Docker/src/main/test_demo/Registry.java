import myrmi.exception.RemoteException;
import myrmi.registry.LocateRegistry;

public class Registry {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry("0.0.0.0");
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("RMI Registry is running...");

    }
}
