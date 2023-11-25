package myrmi.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import myrmi.Remote;

import java.io.Serializable;

@AllArgsConstructor
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
