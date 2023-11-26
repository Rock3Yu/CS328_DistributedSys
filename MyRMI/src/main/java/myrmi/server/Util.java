package myrmi.server;

import myrmi.Remote;

import java.lang.reflect.Proxy;

public class Util {
    public static Remote createStub(RemoteObjectRef ref) {
        // done: finish here, instantiate an StubInvocationHandler for ref and then return a stub
        Class objClass = Remote.class;
        try {
            objClass = Class.forName(ref.getInterfaceName());
        } catch (ClassNotFoundException ignored) {
        }
        StubInvocationHandler stubInvocationHandler = new StubInvocationHandler(ref);
        Object stub = Proxy.newProxyInstance(
                objClass.getClassLoader(),
                new Class[]{objClass},
                stubInvocationHandler);
        return (Remote) stub;
    }
}
