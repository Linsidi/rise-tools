package com.linsidi.entity;

import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Objects;

@ToString
public class ResultVO<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public static <T> ResultVO<T> success(Integer code, String message, T data) {
        ResultVO<T> resultVO = new ResultVO();
        resultVO.setData(data);
        resultVO.setCode(code == null ? HttpStatus.OK.value() : code);
        resultVO.setMessage(message == null ? HttpStatus.OK.getReasonPhrase() : message);
        return resultVO;
    }

    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> resultVO = new ResultVO();
        resultVO.setData(data);
        resultVO.setCode(HttpStatus.OK.value());
        resultVO.setMessage(HttpStatus.OK.getReasonPhrase());
        return resultVO;
    }

    public static ResultVO<String> success() {
        ResultVO<String> resultVO = new ResultVO();
        resultVO.setCode(HttpStatus.OK.value());
        resultVO.setMessage(HttpStatus.OK.getReasonPhrase());
        return resultVO;
    }

    public static ResultVO<String> error(Integer code, String message) {
        ResultVO<String> resultVO = new ResultVO();
        resultVO.setCode(code == null ? HttpStatus.INTERNAL_SERVER_ERROR.value() : code);
        resultVO.setMessage(message == null ? HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() : message);
        return resultVO;
    }

    public static <T> ResultVO<T> error(Integer code, String message,T data) {
        ResultVO<T> resultVO = new ResultVO();
        resultVO.setData(data);
        resultVO.setCode(code == null ? HttpStatus.INTERNAL_SERVER_ERROR.value() : code);
        resultVO.setMessage(message == null ? HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() : message);
        return resultVO;
    }

    public static ResultVO<String> error() {
        ResultVO<String> resultVO = new ResultVO();
        resultVO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        resultVO.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return resultVO;
    }

    private ResultVO() {
    }

    public Integer getCode() {
        return this.code;
    }

    private void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    private void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ResultVO<?> resultVO = (ResultVO)o;
            return this.code.equals(resultVO.code) && this.message.equals(resultVO.message) && this.data.equals(resultVO.data);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.code, this.message, this.data});
    }

//    public String toString() {
//        return "ResultVO{code=" + this.code + ", message='" + this.message + '\'' + ", data=" + this.data + '}';
//    }
}
