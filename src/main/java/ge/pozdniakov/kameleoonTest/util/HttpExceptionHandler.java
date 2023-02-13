package ge.pozdniakov.kameleoonTest.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestControllerAdvice
public class HttpExceptionHandler {
    @ExceptionHandler
    private ResponseEntity<KameleoonErrorResponse> handleException(KameleoonException e){
        KameleoonErrorResponse userErrorResponse = new KameleoonErrorResponse(
                e.getMessage(),
                Timestamp.valueOf(LocalDateTime.now()).getTime()
        );
        return new ResponseEntity<>(userErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
