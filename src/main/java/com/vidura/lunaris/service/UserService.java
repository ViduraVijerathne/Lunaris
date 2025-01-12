package com.vidura.lunaris.service;

import com.vidura.lunaris.dto.UserDTO;
import com.vidura.lunaris.entity.UserEntity;
import com.vidura.lunaris.exception.ValidationException;
import com.vidura.lunaris.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String EXISTING_EMAIL = "test@test.com";
    private static final String OTHER_EMAIL = "other@test.com";

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public Optional<UserEntity> findByEmail(String email) {
        // All of this code would come from the database, but for simplicity of this example
        // Let's just fake it
        return userRepository.findUserEntitiesByEmail(email);

//        if (EXISTING_EMAIL.equalsIgnoreCase(email)) {
//            var user = new UserEntity();
//            user.setId(1L);
//            user.setEmail(EXISTING_EMAIL);
//            user.setPassword("$2a$12$OBnerD3ZrnkqY/ofkaxune1jnpUscFhTGCcuVA9x5lgAGAtr6Bss2"); // test
//            user.setRole("ROLE_ADMIN");
//            user.setExtraInfo("My nice admin user");
//            return Optional.of(user);
//        } else if (OTHER_EMAIL.equalsIgnoreCase(email)) {
//            var user = new UserEntity();
//            user.setId(99L);
//            user.setEmail(OTHER_EMAIL);
//            user.setPassword("$2a$12$OBnerD3ZrnkqY/ofkaxune1jnpUscFhTGCcuVA9x5lgAGAtr6Bss2"); // test
//            user.setRole("ROLE_USER");
//            user.setExtraInfo("My nice simple user");
//            return Optional.of(user);
//        } else {
//            return Optional.empty();
//        }
    }

    public Optional<UserDTO> register(UserDTO userDTO)throws ValidationException {
        userDTO.validate();
        Optional<UserEntity> exist = userRepository.findUserEntitiesByEmail(userDTO.getEmail());
        if (exist.isPresent()) {
            throw new ValidationException("Email already exists");
        }
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        userRepository.save(userEntity);
        UserDTO savedUserDTO = modelMapper.map(userEntity, UserDTO.class);
//        student.validate();
//        StudentEntity studentEntity =  modelMapper.map(student,StudentEntity.class);
//        StudentEntity saved = studentRepository.save(studentEntity);
//        return Optional.of(modelMapper.map(saved,UserDTO.class));
        return  Optional.of(savedUserDTO);
    }
}
