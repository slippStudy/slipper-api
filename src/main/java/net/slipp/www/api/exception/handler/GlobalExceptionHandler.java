package net.slipp.www.api.exception.handler;

import net.slipp.www.api.exception.NotFoundException;
import net.slipp.www.api.support.ResponseWrapper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ResponseWrapper<Object> handleNotFound() {
        return ResponseWrapper.notFound(null);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseWrapper<Object> handleInternalFail() {
        return ResponseWrapper.internalFail(null);
    }
}
