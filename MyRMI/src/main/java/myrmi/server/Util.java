package myrmi.server;

import myrmi.Remote;
import myrmi.registry.Registry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class Util {
    public static Remote createStub(RemoteObjectRef ref) {
        // done: finish here, instantiate an StubInvocationHandler for ref and then return a stub
        StubInvocationHandler stubInvocationHandler = new StubInvocationHandler(ref);
        Object stub = Proxy.newProxyInstance(
                Registry.class.getClassLoader(),
                new Class[]{Registry.class},
                stubInvocationHandler
        );
        return (Remote) stub;
    }
}
