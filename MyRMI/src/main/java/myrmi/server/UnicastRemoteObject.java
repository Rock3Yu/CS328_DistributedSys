package myrmi.server;

import myrmi.Remote;
import myrmi.exception.RemoteException;

import static myrmi.server.Util.createStub;


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

    /**
     * done
     *  1. create a skeleton of the given object ''obj'' and bind with the address ''host:port''
     *  2. return a stub of the object ( Util.createStub() )
     **/
    public static Remote exportObject(Remote obj, String host, int port) throws RemoteException {
        int objectKey = obj.hashCode();
        Skeleton skeleton = new Skeleton(obj, host, port, objectKey);
        skeleton.start();
        while (skeleton.getState() != Thread.State.RUNNABLE) {
            try {
                Thread.sleep(100);  // wait until the skeleton thread running
            } catch (InterruptedException ignored) {
            }
        }
        String interfaceName = "Remote";
        if (obj.getClass().getInterfaces().length > 0) interfaceName = obj.getClass().getInterfaces()[0].getName();
        RemoteObjectRef ref = new RemoteObjectRef(host, port, objectKey, interfaceName);
        return createStub(ref);
    }
}
