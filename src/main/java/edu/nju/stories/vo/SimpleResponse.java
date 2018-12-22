package edu.nju.stories.vo;


import edu.nju.stories.constants.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleResponse {

    private int code;
    private Object data;

    public static SimpleResponse OK(Object object){
        return new SimpleResponse(ResponseCode.OK, object);
    }
}
