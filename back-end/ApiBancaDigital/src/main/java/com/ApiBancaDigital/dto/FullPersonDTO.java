        package com.ApiBancaDigital.dto;

        import jakarta.validation.constraints.Email;
        import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
        import jakarta.validation.constraints.NotNull;
        import jakarta.validation.constraints.Size;
        import java.util.Date;
        import lombok.*;
        import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullPersonDTO {

    
    private int num_person;
    
    @NotNull
    @Size(min = 1, max = 10, message = "Debe ser entre 1 y 10 caracteres")
    private String num_dni;

    @NotNull
    @Size(min = 1, max = 30, message = "Debe ser entre 1 y 30 caracteres")
    private String name;

    @NotNull
    @Size(min = 1, max = 30, message = "Debe ser entre 1 y 30 caracteres")
    private String lastName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @Size(min = 8, max = 20, message = "Debe ser entre 8 y 20 caracteres")
    private String phone;

    //@NotNull(message = "boolField cannot be null")
    private boolean state;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastModify;

    //Datos de User

    private int num_user;

    //@NotNull
    private boolean isAdmin;
 
    private String userName;

    @NotNull
    @Size(min = 6, max = 6, message
            = "Debe ser de 6 caracteres alfanumericos")
    private String password;

    private String userAlias;

    //@NotNull
    @Min(0)
    private int failAttemps;

}