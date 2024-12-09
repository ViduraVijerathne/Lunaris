package com.vidura.lunaris.repository;

import com.vidura.lunaris.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity,Integer> {
    Optional<DistrictEntity> getDistrictEntitiesByName(String name);
}
