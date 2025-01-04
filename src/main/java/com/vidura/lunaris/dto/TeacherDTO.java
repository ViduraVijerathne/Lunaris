package com.vidura.lunaris.dto;

import com.vidura.lunaris.exception.ValidationException;
import lombok.*;
import org.springframework.stereotype.Service;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String nic;
    private String slogan;
    private String education;
    private UserDTO userDTO;
    private SubjectDTO subjectDTO;

    public boolean validate()throws ValidationException {
        if(firstName == null || firstName.isEmpty()){
            throw new ValidationException("please enter first name");
        }
        if(lastName == null || lastName.isEmpty()){
            throw new ValidationException("please enter last name");
        }
        if(email == null || email.isEmpty()){
            throw new ValidationException("please enter email");
        }
        if(phone == null || phone.isEmpty()){
            throw new ValidationException("please enter phone number");
        }
        if(nic == null || nic.isEmpty()){
            throw new ValidationException("please enter nic");
        }
        if(slogan == null || slogan.isEmpty()){
            throw new ValidationException("please enter slogan");
        }
        if(education == null || education.isEmpty()){
            throw new ValidationException("please enter education");
        }
        if(userDTO == null){
            throw new ValidationException("user data part is cannot be null");
        }
        userDTO.validate();
        return true;
    }
}
