package com.vidura.lunaris.service;

import com.vidura.lunaris.dto.TeacherDTO;
import com.vidura.lunaris.entity.TeacherEntity;
import com.vidura.lunaris.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    public Optional<TeacherDTO> save(TeacherDTO teacherDTO) {
        TeacherEntity teacherEntity = modelMapper.map(teacherDTO, TeacherEntity.class);
        teacherEntity.setId(null);
        System.out.println("TEACHER ID IS "+teacherEntity.getId());
        teacherEntity = teacherRepository.save(teacherEntity);
        return Optional.of(modelMapper.map(teacherEntity, TeacherDTO.class));
    }
}
