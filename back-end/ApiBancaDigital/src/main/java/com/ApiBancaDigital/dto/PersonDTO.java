package com.ApiBancaDigital.dto;

import com.ApiBancaDigital.entity.Person;
import com.ApiBancaDigital.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTO {
    
    private int num_person;
    
    //private User user;
    private UserSecureDTO userSecureDto;
    //consultar
    @NotNull
    @Size(min = 7, max = 8, message = "Debe ser entre 7 y 8 digitos")
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
    @Size(min = 8, max = 20, message = "Debe ser entre 1 y 20 caracteres")
    private String phone;
    
    //@NotNull(message = "boolField cannot be null")
    private boolean state;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastModify;

    private User user;
    
    public Person DTOToEntity() {

        Person.PersonBuilder person = Person.builder();

        person.num_person( this.getNum_person() );
        person.numDni( this.getNum_dni() );
        person.name( this.getName() );
        person.lastName( this.getLastName() );
        person.birth( this.getBirth() );
        person.phone( this.getPhone() );
        person.state( this.isState() );
        person.creationDate( this.getCreationDate() );
        person.lastModify( this.getLastModify() );
        person.email(this.getEmail());

        if(this.getUserSecureDto() == null){
            person.user( null );
        }else {
            person.user(this.getUserSecureDto().DTOToUser());
        }

        return person.build();
    }
    
    @Override
    public String toString() {
        return "PersonDTO{" +
                "num_person=" + num_person +
                ", num_dni=" + num_dni +
                ", name='" + name + 
                ", lastName='" + lastName + 
                ", birth='" + birth + 
                ", phone=" + phone +
                ", state=" + state +
                ", creationDate=" + creationDate +
                ", lastModify=" + lastModify +
                ", email=" + email +
                ", user=" + this.getUserSecureDto().toString() + 
                '}';
    }
}
