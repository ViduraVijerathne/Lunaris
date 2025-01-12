package com.vidura.lunaris.service;

import com.vidura.lunaris.dto.UserDTO;
import com.vidura.lunaris.entity.UserEntity;
import com.vidura.lunaris.exception.ValidationException;
import com.vidura.lunaris.model.LoginResponse;
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

//    public StudentRegistrationModel registerStudent(StudentRegistrationModel studentRegistration) throws ValidationException {
//
//        studentRegistration.getStudent().setEmail(studentRegistration.getUser().getEmail());
//        studentRegistration.getUser().setRole("ROLE_USER");
//        String password = studentRegistration.getUser().getPassword();
//        String encoded = passwordEncoder.encode(password);
//        studentRegistration.getUser().setPassword(encoded);
//
//        studentRegistration.getStudent().validate();
//        studentRegistration.getUser().validate();
//        if(studentRegistration.getStudent().getDistrict() != null){
//            if(!districtService.districtExist(studentRegistration.getStudent().getDistrict().getId())){
//                throw  new ValidationException("District does not exist");
//            }
//        }else{
//            throw new ValidationException("Please select a district");
//        }
//        if(studentRegistration.getStudent().getSubjects() != null){
//            for(SubjectDTO subject : studentRegistration.getStudent().getSubjects()){
//                if(!subjectService.subjectExists(subject.getId())){
//                    throw  new ValidationException("Subject does not exist ID:"+subject.getId());
//                }
//            }
//        }else{
//            throw new ValidationException("Please select a subject");
//        }
//
//
//        //register user
//       Optional<UserDTO> savedUser =  userService.save(studentRegistration.getUser());
//
//        if(savedUser.isPresent()) {
//            StudentDTO studentDTO = studentRegistration.getStudent();
//            studentDTO.setUser(savedUser.get());
//            studentService.save(studentDTO);
//        }else{
//            throw new ValidationException("something went wrong in here AUTH SERVICE | REGISTER STUDENT");
//        }
//        return  studentRegistration;
//
//    }
//    public TeacherDTO register(TeacherDTO teacherDTO)throws ValidationException{
//        if(teacherDTO.getUserDTO() == null){
//            teacherDTO.validate();
//        }
//        teacherDTO.getUserDTO().setRole("ROLE_TEACHER");
//        teacherDTO.setEmail(teacherDTO.getUserDTO().getEmail());
//        teacherDTO.validate();
//
//        Optional<UserEntity> existEmailUser = userService.findByEmail(teacherDTO.getUserDTO().getEmail());
//        if(existEmailUser.isPresent()){
//            throw  new ValidationException("Email already exist");
//        }
//
//
//        if(!subjectService.subjectExists(teacherDTO.getSubjectDTO().getId())){
//            throw  new ValidationException("Subject not exist");
//        }
//
//        Optional<UserDTO> savedUserDTO = userService.save(teacherDTO.getUserDTO());
//        if(savedUserDTO.isPresent()) {
//            teacherDTO.setUserDTO(savedUserDTO.get());
//            Optional<TeacherDTO> savedTeacher = teacherService.save(teacherDTO);
//            if(savedTeacher.isPresent()){
//                savedTeacher.get().setSubjectDTO(teacherDTO.getSubjectDTO());
//                savedTeacher.get().setUserDTO(savedUserDTO.get());
//                return  savedTeacher.get();
//            }else{
//                throw  new ValidationException("something went wrong teacher is not save AUTHSERVICE | REGISTER TEACHER ");
//            }
//        }else{
//            throw new  ValidationException("something went wrong user is not save  AUTHSERVICE | REGISTER TEACHER");
//        }
//
//    }
}
