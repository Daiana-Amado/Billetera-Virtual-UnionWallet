package com.ApiBancaDigital.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePersonDTO {

    @Email
    private String personEmail;
    
    @Size(min = 8, max = 20, message = "Debe ser entre 1 y 20 caracteres")
    private String personPhone;
}
