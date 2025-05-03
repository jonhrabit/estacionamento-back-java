package br.com.auth.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ItemNotFoundExcepion.class)
    public ResponseEntity<Object> handleItemNotFoundExcepion(
            ItemNotFoundExcepion exception, WebRequest request) {

        return ResponseEntity.ok(exception.getMessage());
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> defaultErrorHandler(HttpServletRequest req, Exception e) {
        return ResponseEntity.ok(e.toString());
    }

}
