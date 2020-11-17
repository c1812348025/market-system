package com.example.marketsystem.exception;

import com.example.marketsystem.utils.JosnData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



/***
 * 异常处理类
 */
@ControllerAdvice
public class ComstomExceptionHandler {

    private final static Logger logger = (Logger) LoggerFactory.getLogger(ComstomExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JosnData handler(Exception e){
        logger.error("[  系统异常 ] {}",e);
        if(e instanceof XDException){
            XDException xdException =(XDException) e;
            return JosnData.buildError(xdException.getCode(),xdException.getMsg());
        } else if (e instanceof PubGetException){
            PubGetException pubGetException = (PubGetException)e;
            return JosnData.buildError(-1,"获取公共资源出错");
        }else {
            return  JosnData.buildError(-1,"全局异常，未知错误");
        }
    }
}
