package com.vidura.lunaris.model;

import com.vidura.lunaris.dto.StudentDTO;
import com.vidura.lunaris.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentRegistrationModel {
    private UserDTO user;
    private StudentDTO student;
}
