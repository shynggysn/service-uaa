package kz.ne.railways.tezcustoms.service.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
