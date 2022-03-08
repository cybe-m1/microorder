package fges.easyfoodtruck.service.exception;

import org.springframework.http.HttpStatus;

public class FunctionalException extends Exception{
    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private String code;
    public FunctionalException(String message) {
        super(message);
    }
    public FunctionalException(String code, String message) {
        this(message);
        this.code = code;
    }
    public FunctionalException(String message, HttpStatus status) {
        this(message);
        this.status = status;
    }
    public FunctionalException(String code, String message, HttpStatus status) {
        this(message, status);
        this.code = code;
    }
    public String getCode() {
        return code;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
