
package com.ApiBancaDigital.entity;

import java.time.LocalDate;
import java.util.List;


import com.ApiBancaDigital.dto.UserDTO;
import com.ApiBancaDigital.dto.UserSecureDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private int num_user;




    @Column(name="is_admin")
    private boolean isAdmin;

    @Column(unique=true, name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="user_alias")
    private String userAlias;

    @Column(name="fail_attemps")
    private int failAttemps;

    @Column(name="state")
    private boolean state;

    @CreationTimestamp
    @Column(name="creation_date")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Column(name="last_modify")
    private LocalDate lastModify;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Cuenta> cuentas;

    public UserDTO entityToDTO() {

        UserDTO userDTO = new UserDTO();

        userDTO.setAdmin( this.isAdmin() );
        userDTO.setNum_user( this.getNum_user() );
        userDTO.setCuentas( this.getCuentas() );
        userDTO.setUserName( this.getUserName() );
        userDTO.setPassword( this.getPassword() );
        userDTO.setUserAlias( this.getUserAlias() );
        userDTO.setFailAttemps( this.getFailAttemps() );
        userDTO.setState( this.isState() );
        userDTO.setCreationDate( this.getCreationDate() );
        userDTO.setLastModify( this.getLastModify() );

        return userDTO;
    }

    public UserSecureDTO entityToSecureDTO() {

        UserSecureDTO userSecureDTO = new UserSecureDTO();

        userSecureDTO.setNum_user( this.getNum_user() );
        userSecureDTO.setUserName( this.getUserName() );
        userSecureDTO.setUserAlias( this.getUserAlias() );
        userSecureDTO.setFailAttemps( this.getFailAttemps() );
        userSecureDTO.setState( this.isState() );
        userSecureDTO.setCreationDate( this.getCreationDate() );
        userSecureDTO.setLastModify( this.getLastModify() );

        return userSecureDTO;
    }

    @Override
    public String toString() {
        return "User{" +
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
