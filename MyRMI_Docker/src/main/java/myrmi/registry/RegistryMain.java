package myrmi.registry;

import myrmi.exception.RemoteException;

public class RegistryMain {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_HOST);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("RMI Registry is running...");
    }
}
