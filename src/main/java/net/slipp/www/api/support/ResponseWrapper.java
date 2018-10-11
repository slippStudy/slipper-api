package net.slipp.www.api.support;

import java.util.Date;
import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
public class ResponseWrapper<T> {
	
    private long timestamp;

    private int status;

    private String statusMessage;

    private T data;

    public static <T> ResponseWrapper<T> success(T data) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<T>();
        responseWrapper.setTimestamp(new Date().getTime());
        responseWrapper.setStatus(HttpStatus.OK.value());
        responseWrapper.setStatusMessage(HttpStatus.OK.name());
        responseWrapper.setData(data);
        return responseWrapper;
    }

    public static <T> ResponseWrapper<T> internalFail(T data) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<T>();
        responseWrapper.setTimestamp(new Date().getTime());
        responseWrapper.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseWrapper.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
        responseWrapper.setData(data);
        return responseWrapper;
    }

    public static <T> ResponseWrapper<T> notFound(T data) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<T>();
        responseWrapper.setTimestamp(new Date().getTime());
        responseWrapper.setStatus(HttpStatus.NOT_FOUND.value());
        responseWrapper.setStatusMessage(HttpStatus.NOT_FOUND.name());
        responseWrapper.setData(data);
        return responseWrapper;
    }
}
