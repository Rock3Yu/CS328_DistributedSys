import myrmi.registry.LocateRegistry;
import myrmi.registry.Registry;
import myrmi.server.UnicastRemoteObject;

public class ServerMain {

    public ServerMain() {
    }

    public static void main(String[] args) {
        // Usage: java demo.Server <registry host> <server host> <server port>
        try {
            int serverPort = 0;
            String registryHost = null;
            String serverHost = null;

            if (args.length >= 1) {
                if (args.length != 3) {
                    System.out.println("Usage: java demo.Server <registry host> <server host> <server port>");
                    System.exit(1);
                }
                registryHost = args[0];
                serverHost = args[1];
                serverPort = Integer.parseInt(args[2]);
            }

            // (a) get the registry by the registry container’s service name
            System.out.println("---- getRegistry start... ----");
            Registry registry;
            if (registryHost != null) {
                registry = LocateRegistry.getRegistry(registryHost);
            } else {
                registry = LocateRegistry.getRegistry();
            }
            System.out.println("---- getRegistry done ----");

            // (b) export the object
            PaymentImpl robj = new PaymentImpl();
            System.out.println("---- exportObject start... ----");
            Payment stub;
            if (serverHost != null) {
                stub = (Payment) UnicastRemoteObject.exportObject(robj, serverHost, serverPort);
            } else {
                stub = (Payment) UnicastRemoteObject.exportObject(robj, serverPort);
            }
            System.out.println("---- exportObject done ----");

            // (c) register the object to the registry
            registry.rebind("Mortgage", stub);
            System.out.println("Mortgage Server is ready to listen... ");

        } catch (Exception e) {
            System.err.println("Server exception thrown: " + e);
            e.printStackTrace();
        }
    }
}

