package myrmi.server;

import myrmi.Remote;
import myrmi.msg.InvocationMsg;
import myrmi.msg.ReturnMsg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class SkeletonReqHandler extends Thread {
    private final Socket socket;
    private final Remote obj;
    private final int objectKey;

    public SkeletonReqHandler(Socket socket, Remote remoteObj, int objectKey) {
        this.socket = socket;
        this.obj = remoteObj;
        this.objectKey = objectKey;
    }

    @Override
    public void run() {
        int objectKey;
        String methodName;
        Class<?>[] argTypes;
        Object[] args;
        Object result;
        /* done: implement method here, You need to:
         *  1. handle requests from stub, receive invocation arguments, deserialization
         *  2. get result by calling the real object, and handle different cases
         *  Hint: you can use an int to represent the cases:
         *      -1 invocation error,
         *      0  exception thrown,
         *      1  void method,
         *      2  non-void method
         *  */
        ReturnMsg returnMsg = new ReturnMsg();
        returnMsg.setObjectKey(this.objectKey);
        // receive from stub
        try {
            // "in" can not be in try(), it will close both "in" and "socket"
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            InvocationMsg invocationMsg = (InvocationMsg) in.readObject();
            objectKey = invocationMsg.getObjectKey();
            methodName = invocationMsg.getMethodName();
            argTypes = invocationMsg.getParameterTypes();
            args = invocationMsg.getArgs();
            Method method = obj.getClass().getDeclaredMethod(methodName, argTypes);
            if (this.objectKey != objectKey) returnMsg.setStatus(-1);  // invocation error
            else {
                result = method.invoke(obj, args);
                returnMsg.setResult(result);
                if (result == null) returnMsg.setStatus(1);  // void method
                else returnMsg.setStatus(2);  // non-void method
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            returnMsg.setStatus(0);  // exception thrown
            System.err.println("SkeletonReqHandler");
            e.printStackTrace();
        }

        // send to stub
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
            out.writeObject(returnMsg);
            out.flush();
        } catch (IOException e) {
            System.err.println("SkeletonReqHandler");
            e.printStackTrace();
        }
    }
}
