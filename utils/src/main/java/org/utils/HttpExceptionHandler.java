package org.utils;

import io.eventuate.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        if (
                e instanceof NoSuchElementException
                        || e.getCause() instanceof NoSuchElementException
                        || e instanceof NullPointerException
                        || e.getCause() instanceof NoSuchElementException
                        || e instanceof EntityNotFoundException
                        || e.getCause() instanceof EntityNotFoundException
        ) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MyError(e.getMessage(), "Entity not found"));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MyError(e.getMessage(), "Bad request"));
    }

    private class MyError {

        private String error;

        public MyError(String message, String defaultMessage) {
            this.error = (message == null || message.isEmpty()) ? defaultMessage : message;
        }

        public String getError() {
            return this.error;
        }
    }
}