package kz.ne.railways.tezcustoms.service.exception;

import kz.ne.railways.tezcustoms.service.payload.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FLCExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FLCException.class)
    ResponseEntity<Object> handleException(FLCException exception) {
        MessageResponse response = new MessageResponse(exception.getErrorMessage(), exception.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
