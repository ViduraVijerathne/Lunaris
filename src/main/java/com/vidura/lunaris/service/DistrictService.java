package com.vidura.lunaris.service;

import com.vidura.lunaris.dto.DistrictDTO;
import com.vidura.lunaris.entity.DistrictEntity;
import com.vidura.lunaris.exception.ValidationException;
import com.vidura.lunaris.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictRepository districtRepository;
    private final ModelMapper modelMapper;

   public DistrictDTO addDistrict(DistrictDTO districtDTO) throws ValidationException {
        districtDTO.validate();
        DistrictEntity entity = modelMapper.map(districtDTO,DistrictEntity.class);
        entity.setId(null);
        Optional<DistrictEntity> existEntity  = districtRepository.getDistrictEntitiesByName(districtDTO.getName());
        if(existEntity.isPresent()){
            throw new ValidationException("District already exists");
        }
        DistrictEntity savedEntity =  districtRepository.save(entity);
        return  modelMapper.map(savedEntity,DistrictDTO.class);
    }

    public List<DistrictDTO> getAll() {
       List<DistrictEntity> districtEntities = districtRepository.findAll();
       return  districtEntities.stream().map(districtEntity -> modelMapper.map(districtEntity,DistrictDTO.class)).toList();
    }

    public boolean districtExist(Long id){
       Optional<DistrictEntity> districtEntityOptional = districtRepository.getDistrictEntityById(id);
       return districtEntityOptional.isPresent();
    }
}
