package myrmi.msg;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InvocationMsg implements Serializable {
    private int objectKey;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] args;
}
