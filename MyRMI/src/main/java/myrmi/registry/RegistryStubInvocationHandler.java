package myrmi.registry;

import myrmi.exception.AlreadyBoundException;
import myrmi.exception.NotBoundException;
import myrmi.exception.RemoteException;
import myrmi.server.RemoteObjectRef;
import myrmi.server.Util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RegistryStubInvocationHandler implements InvocationHandler {
    private RemoteObjectRef registryRef;
    private Registry registryStub;


    public RegistryStubInvocationHandler(String host, int port) {
        registryRef = new RemoteObjectRef(host, port, 0, "myrmi.registry.Registry");
        registryStub = (Registry) Util.createStub(registryRef);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws RemoteException, AlreadyBoundException, NotBoundException, Throwable {
        Object result;
        try {
            result = method.invoke(registryStub, args);
            System.out.println("RegistryStub invoke " + method.getName());
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
        if ("lookup".equals(method.getName())) {
            // todo: Here you need special handling for invoking ''lookup'' method,
            //  because it returns the stub of a remote object


        }
        return result;
    }
}
