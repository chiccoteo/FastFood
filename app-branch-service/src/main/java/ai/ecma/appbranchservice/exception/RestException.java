package ai.ecma.appbranchservice.exception;

import ai.ecma.appbranchservice.common.MessageService;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RestException extends RuntimeException{
    private String message;
    private HttpStatus status;

    private RestException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public static RestException notFound(String key){
        return new RestException(MessageService.getMessage(key), HttpStatus.NOT_FOUND);
    }

    public static RestException restThrow(String key, HttpStatus status){
        return new RestException(MessageService.getMessage(key), status);
    }

    public static RestException unauthorized(String key){
        return new RestException(MessageService.getMessage(key), HttpStatus.UNAUTHORIZED);
    }

    public static RestException conflict(String key){
        return new RestException(MessageService.getMessage(key), HttpStatus.CONFLICT);
    }

    public static RestException forbidden() {
        return new RestException(MessageService.getMessage("FORBIDDEN"), HttpStatus.FORBIDDEN);
    }
}
