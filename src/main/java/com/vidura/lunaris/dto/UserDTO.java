package com.vidura.lunaris.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
    //this password fromJson ok, but ToJson hid this password field
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

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
        if (!role.equals("STUDENT") && !role.equals("TEACHER")) {
            throw new ValidationException("Role must be \"STUDENT\" or \"TEACHER\"");
        }
        return true;
    }
}