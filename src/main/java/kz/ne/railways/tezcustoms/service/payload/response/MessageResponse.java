package kz.ne.railways.tezcustoms.service.payload.response;

public class MessageResponse {
    private String message;
    private String errorCode;

    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
