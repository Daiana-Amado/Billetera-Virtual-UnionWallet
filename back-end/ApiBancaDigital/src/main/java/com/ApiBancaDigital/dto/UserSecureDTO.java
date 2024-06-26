package com.ApiBancaDigital.dto;

import com.ApiBancaDigital.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSecureDTO {

    private int num_user;

    @NotNull
    @Size(min = 5, max = 10, message
            = "Debe ser entre 5 y 10 caracteres")
    private String userName;

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

        user.num_user( this.getNum_user() );
        user.userName( this.getUserName() );
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
                ", userName='" + userName + '\'' +
                ", userAlias='" + userAlias + '\'' +
                ", failAttemps=" + failAttemps +
                ", state=" + state +
                ", creationDate=" + creationDate +
                ", lastModify=" + lastModify +
                '}';
    }
}
