package com.vidura.lunaris.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ValidationException extends Exception{
    private String message;

}
