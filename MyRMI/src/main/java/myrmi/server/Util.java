package myrmi.server;

import myrmi.Remote;
import myrmi.registry.Registry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class Util {
    public static Remote createStub(RemoteObjectRef ref) {
        Class objClass;
        Class[] interfaces;
        try {
            objClass = Class.forName(ref.getInterfaceName());
        } catch (ClassNotFoundException e) {
            objClass = Remote.class;
        }
        interfaces = new Class[]{objClass};
        StubInvocationHandler stubInvocationHandler = new StubInvocationHandler(ref);
        return (Remote) Proxy.newProxyInstance(
                objClass.getClassLoader(),
                interfaces,
                stubInvocationHandler);
    }

//    public static Remote createStub(RemoteObjectRef ref) {
//        // done: finish here, instantiate an StubInvocationHandler for ref and then return a stub
//        StubInvocationHandler stubInvocationHandler = new StubInvocationHandler(ref);
//        Object stub = Proxy.newProxyInstance(
//                Remote.class.getClassLoader(),
//                new Class[]{Remote.class},
//                stubInvocationHandler
//        );
//        return (Remote) stub;
//    }

    public static Registry createRegistryStub(RemoteObjectRef ref) {
        StubInvocationHandler stubInvocationHandler = new StubInvocationHandler(ref);
        Object stub = Proxy.newProxyInstance(
                Registry.class.getClassLoader(),
                new Class[]{Registry.class},
                stubInvocationHandler
        );
        return (Registry) stub;
    }
}
