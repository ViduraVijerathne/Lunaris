package com.vidura.lunaris.controller;

import com.vidura.lunaris.dto.DistrictDTO;
import com.vidura.lunaris.dto.StudentDTO;
import com.vidura.lunaris.dto.SubjectDTO;
import com.vidura.lunaris.dto.UserDTO;
import com.vidura.lunaris.exception.ValidationException;
import com.vidura.lunaris.model.LoginRequest;
import com.vidura.lunaris.model.LoginResponse;
import com.vidura.lunaris.model.StudentRegistrationModel;
import com.vidura.lunaris.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }

    @PostMapping("/auth/reg")
    public  ResponseEntity reg(@RequestBody @Validated StudentRegistrationModel request) {
//        StudentRegistrationModel studentRegistrationModel = new StudentRegistrationModel();
//        studentRegistrationModel.setStudent(new StudentDTO());
//        studentRegistrationModel.setUser(new UserDTO());
        try{
           var response = authService.registerStudent(request);
            return ResponseEntity.ok(response);
        }catch (ValidationException ex){
            return  ex.getResponse();
        }
    }



}
