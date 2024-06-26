package com.ApiBancaDigital.mapper;

import com.ApiBancaDigital.dto.PersonDTO;
import com.ApiBancaDigital.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(source = "numDni",target = "num_dni")
    PersonDTO EntityToDTO (Person person);
    
    Person DTOToEntity (PersonDTO personDTO);
    
}
