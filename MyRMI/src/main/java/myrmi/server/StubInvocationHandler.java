package myrmi.server;

import myrmi.exception.RemoteException;
import myrmi.msg.InvocationMsg;
import myrmi.msg.ReturnMsg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class StubInvocationHandler implements InvocationHandler, Serializable {
    private String host;
    private int port;
    private int objectKey;

    public StubInvocationHandler(String host, int port, int objectKey) {
        this.host = host;
        this.port = port;
        this.objectKey = objectKey;
        System.out.printf("Stub created to %s:%d, object key = %d\n", host, port, objectKey);
    }

    public StubInvocationHandler(RemoteObjectRef ref) {
        this(ref.getHost(), ref.getPort(), ref.getObjectKey());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws RemoteException, IOException, ClassNotFoundException, Throwable {
        /* done: implement stub proxy invocation handler here
         *  You need to do:
         *  1. connect to remote skeleton, send method and arguments
         *  2. get result back and return to caller transparently
         * */
        Object res = null;
        try (Socket socket = new Socket(host, port);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            // send to skeleton
            InvocationMsg invocationMsg = new InvocationMsg(objectKey, method, args);
            out.writeObject(invocationMsg);
            out.flush();
            // receive from skeleton
            ReturnMsg returnMsg = (ReturnMsg) in.readObject();
            res = returnMsg.getResult();
            return res;
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        return res;
    }

}
