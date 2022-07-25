package ai.ecma.appproductservice.exception;

import ai.ecma.appproductservice.common.MessageService;
import lombok.Data;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;


@Data
public class RestException extends RuntimeException {
    private String message;
    private HttpStatus status;

    public RestException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public static RestException notFound(String key) {
        return new RestException(MessageService.getMessage(key), HttpStatus.NOT_FOUND);
    }

    public static RestException forbidden() {
        return new RestException(MessageService.getMessage("FORBIDDEN"), HttpStatus.FORBIDDEN);
    }
}
