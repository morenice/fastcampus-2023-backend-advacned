package com.fastcampus.flow.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ApplicationAdvice {

    @ExceptionHandler(ApplicationException.class)
    Mono<ResponseEntity<ServerExceptionResponse>> applicationExceptionHandler(ApplicationException ex) {
        return Mono.just(ResponseEntity
                .status(ex.getHttpStatus())
                .body(new ServerExceptionResponse(ex.getCode(), ex.getReason())));
    }

    public record ServerExceptionResponse(String code, String reason) {

    }
}
