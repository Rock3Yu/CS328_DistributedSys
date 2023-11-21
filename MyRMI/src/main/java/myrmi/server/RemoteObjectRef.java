package myrmi.server;

import lombok.Getter;
import lombok.Setter;
import myrmi.Remote;

import java.io.Serializable;

public class RemoteObjectRef implements Serializable, Remote {

    @Getter
    private final String host;
    @Getter
    @Setter
    private int port;
    @Getter
    private final int objectKey;
    @Getter
    private final String interfaceName;

    public RemoteObjectRef(String host, int port, int objectKey, String interfaceName) {
        this.host = host;
        this.port = port;
        this.objectKey = objectKey;
        this.interfaceName = interfaceName;
    }

    public RemoteObjectRef(RemoteObjectRef ref) {
        this.host = ref.host;
        this.port = ref.port;
        this.objectKey = ref.objectKey;
        this.interfaceName = ref.interfaceName;
    }

    @Override
    public String toString() {
        return "RemoteObjectRef{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", objectKey=" + objectKey +
                ", interfaceName='" + interfaceName + '\'' +
                '}';
    }
}
