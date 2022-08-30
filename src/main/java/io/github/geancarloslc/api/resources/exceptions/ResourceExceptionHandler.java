package io.github.geancarloslc.api.resources.exceptions;

import io.github.geancarloslc.api.services.exceptions.ObjectNotFoundExecption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundExecption.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundExecption execption, HttpServletRequest request){
        StandardError error =
                new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), execption.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
