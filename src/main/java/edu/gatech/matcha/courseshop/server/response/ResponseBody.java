package edu.gatech.matcha.courseshop.server.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ResponseBody<T> {

    @Getter
    @Accessors(chain = true)
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    private class Error {
        private Date timestamp;
        private String message;

        Error(String message) {
            this.timestamp = new Date();
            this.message = message;
        }
    }

    @NonNull
    private HttpStatus status;

    private T payload;
    private Object error;
    private Object metadata;

    ResponseBody<T> setError(Error error) {
        this.error = error;
        return this;
    }

    ResponseBody<T> setError(String message) {
        return setError(new Error(message));
    }

    ResponseBody<T> setError(Exception exception) {
        return setError(exception.getMessage());
    }
}
