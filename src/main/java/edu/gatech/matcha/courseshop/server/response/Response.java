package edu.gatech.matcha.courseshop.server.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {
    public static ResponseEntity create(HttpStatus status) {
        return create(status, null);
    }

    public static <T> ResponseEntity<ResponseBody<T>> create(HttpStatus status, T payload) {
        ResponseBody<T> body = new ResponseBody<>(status);
        body.setPayload(payload);
        return ResponseEntity.status(status)
                             .body(body);
    }

    public static <T> ResponseEntity<ResponseBody<T>> create(HttpStatus status, String errorMessage) {
        ResponseBody<T> body = new ResponseBody<>(status);
        body.setError(errorMessage);
        return ResponseEntity.status(status)
                             .body(body);
    }
}

