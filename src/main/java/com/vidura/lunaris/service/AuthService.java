package com.vidura.lunaris.service;

import com.vidura.lunaris.dto.StudentDTO;
import com.vidura.lunaris.dto.UserDTO;
import com.vidura.lunaris.entity.UserEntity;
import com.vidura.lunaris.exception.ValidationException;
import com.vidura.lunaris.model.LoginResponse;
import com.vidura.lunaris.model.StudentRegistrationModel;
import com.vidura.lunaris.security.JwtIssuer;
import com.vidura.lunaris.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final StudentService studentService;

    public LoginResponse attemptLogin(String email, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();

        var token = jwtIssuer.issue(JwtIssuer.Request.builder()
                .userId(principal.getUserId())
                .email(principal.getEmail())
                .roles(principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build());

        return LoginResponse.builder()
                .token(token)
                .build();
    }

    public StudentRegistrationModel registerStudent(StudentRegistrationModel studentRegistration) throws ValidationException {

        studentRegistration.getStudent().setEmail(studentRegistration.getUser().getEmail());
        studentRegistration.getUser().setRole("ROLE_USER");
        String password = studentRegistration.getUser().getPassword();
        String encoded = passwordEncoder.encode(password);
        studentRegistration.getUser().setPassword(encoded);

        studentRegistration.getStudent().validate();
        studentRegistration.getUser().validate();
        //register user
       Optional<UserDTO> savedUser =  userService.save(studentRegistration.getUser());

        if(savedUser.isPresent()) {
            StudentDTO studentDTO = studentRegistration.getStudent();
            studentDTO.setUser(savedUser.get());
            studentService.save(studentDTO);
        }else{
            throw new ValidationException("something went wrong in here AUTH SERVICE | REGISTER STUDENT");
        }
        return  studentRegistration;

    }
}
