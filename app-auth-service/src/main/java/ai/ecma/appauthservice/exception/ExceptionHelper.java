package ai.ecma.appauthservice.exception;

import ai.ecma.appauthservice.common.MessageService;
import ai.ecma.library.payload.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ExceptionHelper {

    @ExceptionHandler(RestException.class)
    public HttpEntity<?> exceptionHandler(RestException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(exception.getStatus()).body(ApiResult.errorResponse(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public HttpEntity<?> exceptionHandler(Exception exception) {
        exception.printStackTrace();
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult.errorResponse(MessageService.getMessage("INTERNAL_SERVER_ERROR")));
    }


}
