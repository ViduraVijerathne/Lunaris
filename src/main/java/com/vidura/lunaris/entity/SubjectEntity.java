package com.vidura.lunaris.entity;

import com.vidura.lunaris.dto.SubjectDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "subjects")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private  String name;


    public SubjectDTO map(ModelMapper modelMapper){
        return  modelMapper.map(this, SubjectDTO.class);
    }
}
