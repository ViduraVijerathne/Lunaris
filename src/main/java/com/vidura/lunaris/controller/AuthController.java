package com.vidura.lunaris.controller;

import com.vidura.lunaris.dto.StudentDTO;
import com.vidura.lunaris.dto.UserDTO;
import com.vidura.lunaris.exception.ValidationException;
import com.vidura.lunaris.model.LoginRequest;
import com.vidura.lunaris.model.LoginResponse;
import com.vidura.lunaris.model.StudentRegistrationModel;
import com.vidura.lunaris.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }

    @PostMapping("/auth/register/student")
    public ResponseEntity reg(@RequestBody @Validated StudentRegistrationModel request) {

        try{
           var response = authService.registerStudent(request);
            return ResponseEntity.ok(response);
        }catch (ValidationException ex){
            return  ex.getResponse();
        }
    }

//    @GetMapping("/public/test")
//    public StudentRegistrationModel test() {
//
//        var stdnt =   new StudentRegistrationModel();
//        stdnt.setUser(new UserDTO());
//        stdnt.setStudent(new StudentDTO());
//        return  stdnt;
//    }



}
