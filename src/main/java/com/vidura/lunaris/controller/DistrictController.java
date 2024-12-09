package com.vidura.lunaris.controller;

import com.vidura.lunaris.dto.DistrictDTO;
import com.vidura.lunaris.exception.ValidationException;
import com.vidura.lunaris.service.DistrictService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    @PostMapping("/admin/district")
    public ResponseEntity<?> addDistrict(@RequestBody DistrictDTO districtDTO){
        try{
            DistrictDTO district =  districtService.addDistrict(districtDTO);
            return ResponseEntity.ok(district);
        }catch (ValidationException ex){
            return ResponseEntity.badRequest().body(ex);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body(ex);
        }
    }

    @GetMapping("/public/district")
    public ResponseEntity<?> getDistrict(){
        try {
            List<DistrictDTO>  districtDTOS =  districtService.getAll();
            return ResponseEntity.ok(districtDTOS);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body(ex);
        }
    }
}
