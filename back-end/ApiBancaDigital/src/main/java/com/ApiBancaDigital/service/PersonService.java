package com.ApiBancaDigital.service;

import com.ApiBancaDigital.dto.EmailDTO;
import com.ApiBancaDigital.dto.FullPersonDTO;
import com.ApiBancaDigital.dto.PersonDTO;
import com.ApiBancaDigital.dto.PersonResponseDTO;
import com.ApiBancaDigital.dto.UpdatePersonDTO;
import com.ApiBancaDigital.entity.Person;
import com.ApiBancaDigital.entity.User;
import com.ApiBancaDigital.persistence.PersonRepository;
import com.ApiBancaDigital.persistence.UserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class PersonService implements IPersonService{

    @Autowired 
    private  PersonRepository personRepository;
    
    @Autowired 
    private  UserRepository userRepository;

    @Override
    public ResponseEntity<PersonResponseDTO> findPersonByNum(int personNum){
        Optional<Person> optionalPerson = personRepository.findById(personNum);
        if(optionalPerson.isEmpty()){
            return ResponseEntity.badRequest().body(new PersonResponseDTO(
                    "La Persona con numero: " + personNum + " no existe!",
                    null
            ));
        }
        return ResponseEntity.ok().body(new PersonResponseDTO(
                "La Persona numero: " + personNum + " encontrado con exito!",
                List.of(optionalPerson.get().EntityToDTO())
        ));
    }
    
    @Override
    public ResponseEntity<PersonResponseDTO> getPersons() {
        List<Person> personList = personRepository.findAll();
        List<PersonDTO> personDtoList = new ArrayList<>();
        for(Person person : personList){
            PersonDTO personDto = person.EntityToDTO();
            personDtoList.add(personDto);
        }
        return ResponseEntity.ok().body(new PersonResponseDTO(
                "Lista de personas en el sistema",
                personDtoList
        ));
    }
    
    @Override
    public ResponseEntity<PersonResponseDTO> createPerson(PersonDTO newPersonDto) {
        
        //Person person = personRepository.findByEmail(newPersonDto.getEmail());
        
        if(personRepository.existsByNumDni(newPersonDto.getNum_dni())){
            return ResponseEntity.badRequest().body(new PersonResponseDTO(
                    "Persona con DNI : " + newPersonDto.getNum_dni() + " ya se encuentra registrado!",
                    null
            ));
        }
        
        if(personRepository.existsByEmail(newPersonDto.getEmail())){
            return ResponseEntity.badRequest().body(new PersonResponseDTO(
                    "El Email: " + newPersonDto.getEmail() + " ya se encuentra registrado!",
                    null
            ));
        }
        
        Person newPerson = newPersonDto.DTOToEntity();
        newPerson.setState(true);
        Person personCreated = personRepository.save(newPerson);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new PersonResponseDTO(
                "Nuevo registro: " + newPerson.getName() + " " + newPerson.getLastName() + " creado con exito!",
                List.of(personCreated.EntityToDTO())
        ));
    }
    
    @Override
    public ResponseEntity<PersonResponseDTO> createFullPerson(FullPersonDTO newFullPersonDto) {
        
        if(personRepository.existsByNumDni(newFullPersonDto.getNum_dni())){
            return ResponseEntity.badRequest().body(new PersonResponseDTO(
                    "Persona con DNI : " + newFullPersonDto.getNum_dni() + " ya se encuentra registrado!",
                    null
            ));
        }
        
        if(personRepository.existsByEmail(newFullPersonDto.getEmail())){
            return ResponseEntity.badRequest().body(new PersonResponseDTO(
                    "El Email: " + newFullPersonDto.getEmail() + " ya se encuentra registrado!",
                    null
            ));
        }
        
        if(userRepository.existsByUserName(newFullPersonDto.getUserName())){
            return ResponseEntity.badRequest().body(new PersonResponseDTO(
                    "El user name : " + newFullPersonDto.getUserName() + " ya se encuentra registrado!",
                    null
            ));
        }
        
        Person newPerson = new Person();
        User newUser = new User();
        
        newPerson.setNumDni(newFullPersonDto.getNum_dni());
        newPerson.setName(newFullPersonDto.getName());
        newPerson.setLastName(newFullPersonDto.getLastName());
        newPerson.setBirth(newFullPersonDto.getBirth());
        newPerson.setEmail(newFullPersonDto.getEmail());
        newPerson.setPhone(newFullPersonDto.getPhone());
        newPerson.setState(true);
        newUser.setUserName(newFullPersonDto.getUserName());
        newUser.setUserAlias(newFullPersonDto.getUserName()+"_alias");
        newUser.setPassword(newFullPersonDto.getPassword());
        newUser.setFailAttemps(0);
        newUser.setState(true);
        newUser.setAdmin(false);
        newPerson.setUser(newUser);
        newPerson = personRepository.save(newPerson);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new PersonResponseDTO(
                "Nuevo registro: " + newPerson.getName() + " " + newPerson.getLastName() + " creado con exito!",
                List.of(newPerson.EntityToDTO())
        ));
    }
    
    @Override
    public ResponseEntity<PersonResponseDTO> deletePerson(String personEmail) {
        Person person = personRepository.findByEmail(personEmail);
        if(person == null){
            return ResponseEntity.badRequest().body(new PersonResponseDTO(
                    "La persona con Email : " + personEmail + " no existe!",
                    null
            ));
        }
        personRepository.deleteById(person.getNum_person());
        
        return ResponseEntity.ok().body(new PersonResponseDTO(
                "Persona: " + person.getName() + " " + person.getLastName() + " eliminado con exito!",
                List.of(person.EntityToDTO())
        ));
    }
    
    @Override
    @Transactional
    public ResponseEntity<PersonResponseDTO> updatePerson(UpdatePersonDTO updatePersonDTO, EmailDTO personEmailDTO) {
        
        Person personToUpdate = personRepository.findByEmail(personEmailDTO.getEmail());
        if(personToUpdate == null){
            return ResponseEntity.badRequest().body(new PersonResponseDTO(
                    "La persona con email: " + personEmailDTO.getEmail() + " no existe!",
                    null
            ));
        }

        
        String personEmail = updatePersonDTO.getPersonEmail();
        String personPhone = updatePersonDTO.getPersonPhone();

        if( personPhone != null && personPhone.length() > 0 && !Objects.equals(personToUpdate.getPhone(), personPhone)){
            personToUpdate.setPhone(personPhone);
        }

        if(personEmail != null && personRepository.existsByEmail(personEmail)){
            return ResponseEntity.ok().body(new PersonResponseDTO(
                    "El correo: "+ personEmail + " ya esta en uso!",
                    List.of(personToUpdate.EntityToDTO())
            ));
        }
        if( personEmail != null && personEmail.length() > 0 && !Objects.equals(personToUpdate.getEmail(), personEmail)){
            personToUpdate.setEmail(personEmail);
        }

        return ResponseEntity.ok().body(new PersonResponseDTO(
                "Persona: " + personToUpdate.getName() + " " + personToUpdate.getLastName() + " modficado con exito!",
                List.of(personToUpdate.EntityToDTO())
        ));
    }
    
    
    @Override
    public ResponseEntity<PersonResponseDTO> findPersonByEmail(String personEmail) {
        Person person = personRepository.findByEmail(personEmail);
        if(person == null){
            return ResponseEntity.badRequest().body(new PersonResponseDTO(
                    "La persona con email: " + personEmail + " no existe!",
                    null
            ));
        }

        return ResponseEntity.ok().body(new PersonResponseDTO(
                "Persona con email: " + person.getEmail() + " encontrado con exito!",
                List.of(person.EntityToDTO())
        ));
    }
  
}