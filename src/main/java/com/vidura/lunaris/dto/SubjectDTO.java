package com.vidura.lunaris.dto;

import com.vidura.lunaris.entity.SubjectEntity;
import com.vidura.lunaris.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private Long id;
    private String name;

    public  boolean validate()throws ValidationException{
        if(name == null || name.isEmpty()){
            throw new ValidationException("Subject Name is empty");
        }
        if(name.length() <= 2){
            throw new ValidationException("Subject Name is too short");
        }
        if(name.length() > 255){
            throw new ValidationException("Subject Name is too long");
        }
        return  true;
    }

    public SubjectEntity map(ModelMapper modelMapper){
        return  modelMapper.map(this, SubjectEntity.class);
    }
}

