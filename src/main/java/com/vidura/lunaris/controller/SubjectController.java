package com.vidura.lunaris.controller;

import com.vidura.lunaris.dto.SubjectDTO;
import com.vidura.lunaris.exception.ValidationException;
import com.vidura.lunaris.model.DefaultResponse;
import com.vidura.lunaris.model.DefaultResponseType;
import com.vidura.lunaris.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    @PostMapping("/admin/subject")
    public ResponseEntity<?> addSubject(@RequestBody SubjectDTO subject) {
        try{
           SubjectDTO subjectDTO =  subjectService.addSubject(subject);
           return  ResponseEntity.ok(subjectDTO);
        }catch (ValidationException ex){
            return ResponseEntity.badRequest().body(ex);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body(ex);
        }

    }

}
