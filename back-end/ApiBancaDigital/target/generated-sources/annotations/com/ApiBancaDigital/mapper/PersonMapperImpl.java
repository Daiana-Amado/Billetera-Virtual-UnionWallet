package com.ApiBancaDigital.mapper;

import com.ApiBancaDigital.dto.PersonDTO;
import com.ApiBancaDigital.entity.Person;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-16T14:47:42-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonDTO EntityToDTO(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonDTO.PersonDTOBuilder personDTO = PersonDTO.builder();

        personDTO.num_person( person.getNum_person() );
        personDTO.user( person.getUser() );
        personDTO.num_dni( person.getNum_dni() );
        personDTO.name( person.getName() );
        personDTO.lastName( person.getLastName() );
        personDTO.birth( person.getBirth() );
        personDTO.email( person.getEmail() );
        personDTO.phone( person.getPhone() );
        personDTO.state( person.isState() );
        personDTO.creationDate( person.getCreationDate() );
        personDTO.lastModify( person.getLastModify() );

        return personDTO.build();
    }

    @Override
    public Person DTOToEntity(PersonDTO personDTO) {
        if ( personDTO == null ) {
            return null;
        }

        Person.PersonBuilder person = Person.builder();

        person.num_person( personDTO.getNum_person() );
        person.user( personDTO.getUser() );
        person.num_dni( personDTO.getNum_dni() );
        person.name( personDTO.getName() );
        person.lastName( personDTO.getLastName() );
        person.birth( personDTO.getBirth() );
        person.email( personDTO.getEmail() );
        person.phone( personDTO.getPhone() );
        person.state( personDTO.isState() );
        person.creationDate( personDTO.getCreationDate() );
        person.lastModify( personDTO.getLastModify() );

        return person.build();
    }
}
