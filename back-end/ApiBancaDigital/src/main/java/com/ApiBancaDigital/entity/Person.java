
package com.ApiBancaDigital.entity;

import com.ApiBancaDigital.dto.PersonDTO;
import jakarta.persistence.*;
import java.util.Date;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Builder
@Data
@AllArgsConstructor
@Table(name = "persona")
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int num_person;
    

    
    @Column (unique=true, name = "num_dni")
    private String numDni;
    
    @Column (name = "nombres")
    private String name;
    
    @Column (name = "apellidos")
    private String lastName;
    
    @Column (name = "fecha_nac")
    private Date birth;
    
    @Column (unique=true, name = "correo")
    private String email;
    
    @Column (name = "telefono")
    private String phone;
    
    @Column (name = "estado")
    private boolean state;
    
    @CreationTimestamp
    private Date creationDate;
    
    @UpdateTimestamp
    private Date lastModify;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="num_user")
    private User user;
    
    public PersonDTO EntityToDTO() {

        PersonDTO personDTO = new PersonDTO();

        personDTO.setNum_person( this.getNum_person() );
        personDTO.setNum_dni( this.getNumDni() );
        personDTO.setName( this.getName() );
        personDTO.setLastName( this.getLastName() );
        personDTO.setBirth( this.getBirth() );
        personDTO.setEmail( this.getEmail() );
        personDTO.setPhone( this.getPhone() );
        personDTO.setState( this.isState() );
        personDTO.setCreationDate( this.getCreationDate() );
        personDTO.setLastModify( this.getLastModify() );

        if(this.getUser() == null){
            personDTO.setUserSecureDto(null);
        }else{
            personDTO.setUserSecureDto(this.getUser().entityToSecureDTO() );
        }

        return personDTO;
    }
    
    
    @Override
    public String toString() {
        return "PersonDTO{" +
                "num_person=" + num_person +
                ", num_dni=" + numDni +
                ", name='" + name + 
                ", lastName='" + lastName + 
                ", birth='" + birth + 
                ", phone=" + phone +
                ", state=" + state +
                ", creationDate=" + creationDate +
                ", lastModify=" + lastModify +
                ", email=" + email +
                ", user=" + this.getUser().toString() + 
                '}';
    }
}

