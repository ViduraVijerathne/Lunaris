package com.vidura.lunaris.dto;

import com.vidura.lunaris.exception.ValidationException;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String role;
    private String extraInfo;

    public boolean validate()throws ValidationException {
        if(email == null || email.isEmpty()){
            throw new ValidationException("Email cannot be empty");
        }
        if(password == null || password.isEmpty()){
            throw new ValidationException("Password cannot be empty");
        }
        if(role == null || role.isEmpty()){
            throw new ValidationException("Role cannot be empty");
        }
        return true;
    }
}