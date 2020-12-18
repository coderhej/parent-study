package coder.yqwh.usr.util;

import java.io.Serializable;

/**
 * @author Administrator
 * @time 2020/12/2 21:50
 */
public class ResponseEntityVO<T> implements Serializable {

    T data;

    String message;

    Integer errCode = 0;

    public boolean succeed() {
        return errCode==0;
    }

    public static <T> ResponseEntityVO<T> success() {
        ResponseEntityVO responseEntity = new ResponseEntityVO();
        return responseEntity;
    }

    public static <T> ResponseEntityVO<T> success(T data) {
        ResponseEntityVO responseEntity = new ResponseEntityVO();
        responseEntity.setData(data);
        return responseEntity;
    }

    public static <T> ResponseEntityVO<T> failure(Integer errCode, String message) {
        ResponseEntityVO responseEntity = new ResponseEntityVO();
        responseEntity.setErrCode(errCode);
        responseEntity.setMessage(message);
        return responseEntity;
    }

    public static <T> ResponseEntityVO<T> failure(ResponseCode code) {
        ResponseEntityVO responseEntity = new ResponseEntityVO();
        responseEntity.setResultCode(code);
        return responseEntity;
    }

    public static <T> ResponseEntityVO<T> failure(ResponseCode code, T data) {
        ResponseEntityVO responseEntity = new ResponseEntityVO();
        responseEntity.setResultCode(code);
        responseEntity.setData(data);
        return responseEntity;
    }

    private void setResultCode(ResponseCode code) {
        this.errCode = code.getCode();
        this.message = code.getTemplate();
    }

    public ResponseEntityVO() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }
}
