package com.ApiBancaDigital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDTO {
    private String msg;
    private List<PersonDTO> obj; 
}
