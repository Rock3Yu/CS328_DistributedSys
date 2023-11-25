package myrmi.server;

import lombok.Getter;
import lombok.Setter;
import myrmi.Remote;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
@Setter
public class Skeleton extends Thread {
    static final int BACKLOG = 5;
    private final Remote remoteObj;
    private final String host;
    private int port;
    private final int objectKey;
    private final ExecutorService threadPool;

    public Skeleton(Remote remoteObj, RemoteObjectRef ref) {
        this(remoteObj, ref.getHost(), ref.getPort(), ref.getObjectKey());
    }

    public Skeleton(Remote remoteObj, String host, int port, int objectKey) {
        super();
        this.remoteObj = remoteObj;
        this.host = host;
        this.port = port;
        this.objectKey = objectKey;
        this.setDaemon(false);  // set to user thread
        this.threadPool = Executors.newFixedThreadPool(10);
    }

    @Override
    public void run() {
        /* done: implement method here
         *  You need to:
         *  1. create a server socket to listen for incoming connections
         *  2. use a handler thread to process each request (use SkeletonReqHandler)
         *  */
        try {
            InetAddress bindAddr = InetAddress.getByName(getHost());
            ServerSocket server = new ServerSocket(getPort(), BACKLOG, bindAddr);
            setPort(server.getLocalPort());
            while (true) {
                Socket client = server.accept();
                threadPool.submit(new SkeletonReqHandler(client, getRemoteObj(), getObjectKey()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
