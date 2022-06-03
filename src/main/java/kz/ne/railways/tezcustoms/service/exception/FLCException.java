package kz.ne.railways.tezcustoms.service.exception;

import lombok.Data;

@Data
public class FLCException extends Exception{

    private String errorMessage;
    private String errorCode;

    public FLCException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public FLCException(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}