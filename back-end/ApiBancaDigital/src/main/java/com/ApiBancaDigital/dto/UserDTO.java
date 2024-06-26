package com.ApiBancaDigital.dto;

import com.ApiBancaDigital.entity.Cuenta;
import com.ApiBancaDigital.entity.User;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int num_user;

    private List<Cuenta> cuentas;

    private boolean isAdmin;

    @NotNull
    @Size(min = 5, max = 10, message
            = "Debe ser entre 5 y 10 caracteres")
    private String userName;

    @NotNull
    @Size(min = 6, max = 6, message
            = "Debe ser de 6 caracteres")
    private String password;

    private String userAlias;

    @Min(0)
    private int failAttemps;

    private boolean state;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastModify;




    public User DTOToUser() {

        User.UserBuilder user = User.builder();

        user.isAdmin( this.isAdmin());
        user.num_user( this.getNum_user() );
        user.cuentas( this.getCuentas());
        user.userName( this.getUserName() );
        user.password( this.getPassword() );
        user.userAlias( this.getUserAlias() );
        user.failAttemps( this.getFailAttemps() );
        user.state( this.isState() );
        user.creationDate( this.getCreationDate() );
        user.lastModify( this.getLastModify() );

        return user.build();
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "num_user=" + num_user +
                ", isAdmin=" + isAdmin +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userAlias='" + userAlias + '\'' +
                ", failAttemps=" + failAttemps +
                ", state=" + state +
                ", creationDate=" + creationDate +
                ", lastModify=" + lastModify +
                '}';
    }
}
