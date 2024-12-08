package com.vidura.lunaris.repository;

import com.vidura.lunaris.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity,Integer> {
    Optional<SubjectEntity> getSubjectEntityByName(String name);
}
