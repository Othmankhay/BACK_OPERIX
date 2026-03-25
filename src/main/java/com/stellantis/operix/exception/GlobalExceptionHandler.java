package com.stellantis.operix.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException e) {
        return ResponseEntity.status(404)
                .body(new ErrorResponse(404, e.getMessage()) {
                    @Override
                    public HttpStatusCode getStatusCode() {
                        return null;
                    }

                    @Override
                    public ProblemDetail getBody() {
                        return null;
                    }
                });
    }

    @ExceptionHandler(JwtAuthException.class)
    public ResponseEntity<ErrorResponse> handleJwt(
            JwtAuthException e) {
        return ResponseEntity.status(401)
                .body(new ErrorResponse(401, e.getMessage()) {
                    @Override
                    public HttpStatusCode getStatusCode() {
                        return null;
                    }

                    @Override
                    public ProblemDetail getBody() {
                        return null;
                    }
                });
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception e) {
        log.error("[UNHANDLED ERROR]", e);
        return ResponseEntity.status(500)
                .body(new ErrorResponse(500, "Erreur serveur") {
                    @Override
                    public HttpStatusCode getStatusCode() {
                        return null;
                    }

                    @Override
                    public ProblemDetail getBody() {
                        return null;
                    }
                });
    }
}