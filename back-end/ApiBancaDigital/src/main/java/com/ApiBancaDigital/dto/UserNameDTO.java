        package com.ApiBancaDigital.dto;
        import jakarta.validation.constraints.NotNull;
        import jakarta.validation.constraints.Size;
        import lombok.Data;

@Data
public class UserNameDTO {

    @NotNull
    @Size(min = 5, max = 10, message
            = "Debe ser entre 5 y 10 caracteres")
    private String userName;
}