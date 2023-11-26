package myrmi.msg;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ReturnMsg implements Serializable {
    private int objectKey;
    private int status;
    private Object result;
}
