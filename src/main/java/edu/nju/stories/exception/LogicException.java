package edu.nju.stories.exception;

import lombok.Data;

@Data
public class LogicException extends RuntimeException {


    /**
     * 错误码
     */
    protected int errCode;
    /**
     * 格式化错误码时所需参数列表
     */
    protected String message;



    /**
     * 构造函数设置错误码以及错误参数列表
     *
     * @param errCode 错误码
     * @param message  错误参数列表
     */
    public LogicException(int errCode, String message) {
        this.errCode = errCode;
        this.message = message;
    }
}
