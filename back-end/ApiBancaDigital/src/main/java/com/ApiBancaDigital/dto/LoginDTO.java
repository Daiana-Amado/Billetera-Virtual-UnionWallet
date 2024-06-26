package com.ApiBancaDigital.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 6, message
            = "Debe ser de 6 caracteres alfanumericos")
    private String password;


}
