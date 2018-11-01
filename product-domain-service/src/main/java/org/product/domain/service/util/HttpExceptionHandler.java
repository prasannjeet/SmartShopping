package org.product.domain.service.util;

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
    public ResponseEntity<?> defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws Exception {
        if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException
                || e instanceof NullPointerException || e.getCause() instanceof NoSuchElementException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MyError(null, "Entity not found"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MyError(e.getMessage(), "Bad request"));
    }

    private class MyError {

        private String error;

        public MyError(String message, String defaultMessage) {
            this.error = (message == null) ? defaultMessage : (message.isEmpty() ? defaultMessage : message);
        }

        public String getError() {
            return this.error;
        }
    }
}