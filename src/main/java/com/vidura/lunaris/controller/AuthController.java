package com.vidura.lunaris.controller;
import com.vidura.lunaris.dto.UserDTO;
import com.vidura.lunaris.exception.ValidationException;
import com.vidura.lunaris.model.DefaultResponse;
import com.vidura.lunaris.model.DefaultResponseType;
import com.vidura.lunaris.model.LoginRequest;
import com.vidura.lunaris.model.LoginResponse;
import com.vidura.lunaris.security.SecurityUtils;
import com.vidura.lunaris.security.UserPrincipal;
import com.vidura.lunaris.service.AuthService;
import com.vidura.lunaris.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;


    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }

    @PostMapping("/me")
    public ResponseEntity<UserPrincipal> me() {
       UserPrincipal principal =  SecurityUtils.getCurrentUser();
       return ResponseEntity.ok(principal);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<DefaultResponse> register(@RequestBody UserDTO user){
        try{
           Optional<UserDTO> optionalUserDTO =  userService.register(user);
            if(optionalUserDTO.isPresent()){
                return  ResponseEntity.ok(DefaultResponse.builder()
                        .data(optionalUserDTO.get())
                        .message("Registration Success!")
                                .responseType(DefaultResponseType.SUCCESS)
                        .build());
            }else{
                return  ResponseEntity.badRequest().body(DefaultResponse.builder()
                                .responseType(DefaultResponseType.FAIL)
                                .message("something went wrong!")

                        .build());
            }
        }catch (ValidationException ex){
            return  ResponseEntity.badRequest().body(DefaultResponse.builder()
                    .responseType(DefaultResponseType.FAIL)
                    .message(ex.getMessage())

                    .build());
        }
    }

//    @PostMapping("/auth/register/student")
//    public ResponseEntity reg(@RequestBody @Validated StudentRegistrationModel request) {
//
//        try{
//           var response = authService.registerStudent(request);
//            return ResponseEntity.ok(response);
//        }catch (ValidationException ex){
//            return  ex.getResponse();
//        }
//    }






}
