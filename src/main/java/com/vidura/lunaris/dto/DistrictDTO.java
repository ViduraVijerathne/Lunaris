package com.vidura.lunaris.dto;

import com.vidura.lunaris.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DistrictDTO {
    private Long id;
    private String name;

    public boolean validate()throws ValidationException {
        if(name == null || name.isEmpty()){
            throw new ValidationException("District Name is null or empty");
        }
        if(name.length() > 25){
            throw new ValidationException("District Name is too long");
        }
        if(name.length() < 2){
            throw new ValidationException("District Name is too short");
        }
        return true;
    }
}
