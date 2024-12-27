package com.vidura.lunaris.dto;

import com.vidura.lunaris.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentDTO {
    private Long id;
    private UserDTO user;
    private String firstName;
    private  String lastName;
    private String email;
    private DistrictDTO district;
    private List<SubjectDTO> subjects;


    public boolean validate()throws ValidationException {
        if(user != null){
            user.validate();
        }
        if(firstName == null || firstName.isEmpty()){
            throw new ValidationException("First name cannot  empty");
        }
        if(lastName  == null ||lastName.isEmpty()){
            throw new ValidationException("Last name cannot be empty");
        }
        if(email  == null ||email.isEmpty()){
            throw new ValidationException("Email cannot be empty");
        }
        if(district == null){
            throw new ValidationException("please, select district");
        }
        return  true;
    }

}
