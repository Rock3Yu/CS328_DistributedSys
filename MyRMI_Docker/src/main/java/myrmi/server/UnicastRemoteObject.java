package myrmi.server;

import myrmi.Remote;
import myrmi.exception.RemoteException;

public class UnicastRemoteObject implements Remote, java.io.Serializable {
    int port;

    protected UnicastRemoteObject() throws RemoteException {
        this(0);
    }

    protected UnicastRemoteObject(int port) throws RemoteException {
        this.port = port;
        exportObject(this, port);
    }

    public static Remote exportObject(Remote obj) throws RemoteException {
        return exportObject(obj, 0);
    }

    public static Remote exportObject(Remote obj, int port) throws RemoteException {
        return exportObject(obj, "127.0.0.1", port);
    }

    /* done
     *  1. create a skeleton of the given object ''obj'' and bind with the address ''host:port''
     *  2. return a stub of the object ( Util.createStub() )
     **/
    public static Remote exportObject(Remote obj, String host, int port) throws RemoteException {
        int objectKey = obj.hashCode();
        Skeleton skeleton = new Skeleton(obj, host, port, objectKey);
        skeleton.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.err.println("UnicastRemoteObject");
            e.printStackTrace();
        }
        String interfaceName = "Remote";
        if (obj.getClass().getInterfaces().length > 0) interfaceName = obj.getClass().getInterfaces()[0].getName();
        int remotePort = skeleton.getPort();
        RemoteObjectRef ref = new RemoteObjectRef(host, remotePort, objectKey, interfaceName);
        return Util.createStub(ref);
    }
}
