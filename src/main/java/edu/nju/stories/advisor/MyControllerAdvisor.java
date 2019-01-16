package edu.nju.stories.advisor;

import edu.nju.stories.constants.ErrorCode;
import edu.nju.stories.exception.LogicException;
import edu.nju.stories.vo.SimpleResponse;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class MyControllerAdvisor {

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public SimpleResponse errorHandler(Exception ex) {
        ex.printStackTrace();
        if (ex instanceof LogicException){
            LogicException exception = (LogicException) ex;
            return new SimpleResponse(exception.getErrCode(), ex.getMessage());
        }else{
            return new SimpleResponse(ErrorCode.UNKNOWN, ex.getMessage());
        }
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = LogicException.class)
    public SimpleResponse myErrorHandler(LogicException ex) {
        ex.printStackTrace();
        return new SimpleResponse(ex.getErrCode(), ex.getMessage());
    }

}
