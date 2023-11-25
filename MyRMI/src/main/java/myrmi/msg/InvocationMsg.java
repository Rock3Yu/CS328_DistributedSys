package myrmi.msg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Method;

@Getter
@Setter
@AllArgsConstructor
public class InvocationMsg implements Serializable {
    private int objectKey;
    private Method method;
    private Object[] args;
}
