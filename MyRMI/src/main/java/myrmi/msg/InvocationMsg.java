package myrmi.msg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class InvocationMsg implements Serializable {
    private int objectKey;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] args;
}
