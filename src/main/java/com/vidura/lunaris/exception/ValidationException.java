package com.vidura.lunaris.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ValidationException extends Exception{
    private String message;

    public ResponseEntity<SimpleValidationException> getResponse(){
        return  ResponseEntity.badRequest().body(new SimpleValidationException(message));
    }

}

@AllArgsConstructor
@Getter
class SimpleValidationException{
    private String message;
}
