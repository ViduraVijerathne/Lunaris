package com.vidura.lunaris.service;

import com.vidura.lunaris.dto.StudentDTO;
import com.vidura.lunaris.entity.StudentEntity;
import com.vidura.lunaris.exception.ValidationException;
import com.vidura.lunaris.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public Optional<StudentDTO> save(StudentDTO studentDTO)throws ValidationException {
        studentDTO.validate();
        Optional<StudentEntity> existStudent = studentRepository.findStudentEntityByEmail(studentDTO.getEmail());
        if (existStudent.isPresent()) {
            throw new ValidationException("Email already exists");
        }
        StudentEntity studentEntity = modelMapper.map(studentDTO,StudentEntity.class);
        studentEntity.setId(null);

        StudentEntity student = studentRepository.save(studentEntity);

        return  Optional.of(modelMapper.map(student,StudentDTO.class));
    }
}
