package com.vidura.lunaris.service;

import com.vidura.lunaris.dto.SubjectDTO;
import com.vidura.lunaris.entity.SubjectEntity;
import com.vidura.lunaris.exception.ValidationException;
import com.vidura.lunaris.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    public SubjectDTO addSubject(SubjectDTO subject)throws ValidationException {
        subject.validate();
        SubjectEntity subjectEntity = subject.map(modelMapper);
        subjectEntity.setId(null);
        Optional<SubjectEntity> existing = subjectRepository.getSubjectEntityByName(subjectEntity.getName());
        if(existing.isPresent()){
            throw new ValidationException("Subject already exists");
        }
        SubjectEntity saved =  subjectRepository.save(subjectEntity);

        return  saved.map(modelMapper);
    }

    public List<SubjectDTO> getAll() {
        List<SubjectEntity> entities =   subjectRepository.findAll();
        return entities.stream()
                .map(entity -> modelMapper.map(entity, SubjectDTO.class))
                .toList();
    }
}
