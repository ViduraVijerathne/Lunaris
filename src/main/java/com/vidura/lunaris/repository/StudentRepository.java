package com.vidura.lunaris.repository;

import com.vidura.lunaris.dto.StudentDTO;
import com.vidura.lunaris.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Integer>{

    Optional<StudentEntity> findStudentEntityByEmail(String email);
}
