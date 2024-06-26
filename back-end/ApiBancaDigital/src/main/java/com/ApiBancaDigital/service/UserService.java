package com.ApiBancaDigital.service;

import com.ApiBancaDigital.dto.*;
import com.ApiBancaDigital.entity.Person;
import com.ApiBancaDigital.entity.User;
import com.ApiBancaDigital.mapper.PersonMapper;
import com.ApiBancaDigital.mapper.UserMapper;
import com.ApiBancaDigital.persistence.PersonRepository;
import com.ApiBancaDigital.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired private  UserRepository userRepository;
    @Autowired private  PersonRepository personRepository;


    public ResponseEntity<UserReponseDTO> findUserByNum(int userNum){
        Optional<User> optionalUser = userRepository.findById(userNum);
        if(optionalUser.isEmpty()){
            return ResponseEntity.badRequest().body(new UserReponseDTO(
                    "El usuario con numero: " + userNum + " no existe!",
                    null
            ));
        }

        return ResponseEntity.ok().body(new UserReponseDTO(
                "Usuario con numero: " + userNum + " encontrado con exito!",
                List.of(optionalUser.get().entityToSecureDTO())
        ));
    }

    public ResponseEntity<UserReponseDTO> getUsers() {
        List<User> userList = userRepository.findAll();
        List<UserSecureDTO> userSecureDtoList = new ArrayList<>();
        for(User user : userList){
            UserSecureDTO userSecureDto = user.entityToSecureDTO();
            userSecureDtoList.add(userSecureDto);
        }

        return ResponseEntity.ok().body(new UserReponseDTO(
                "Lista de usuarios en el sistema",
                userSecureDtoList
        ));
    }

    public ResponseEntity<UserReponseDTO> createUser(UserDTO newUserDTO, String personEmail) {
        Person person = personRepository.findByEmail(personEmail);

        if(person == null){
            return ResponseEntity.badRequest().body(new UserReponseDTO(
                    "La persona con email: " + personEmail + " no existe!",
                    null
            ));
        }

        if(person.getUser() != null){
            return ResponseEntity.badRequest().body(new UserReponseDTO(
                    "La persona con email: " + personEmail + " ya tiene usuario!",
                    null
            ));
        }

        if(userRepository.existsByUserName(newUserDTO.getUserName())){
            return ResponseEntity.badRequest().body(new UserReponseDTO(
                    "El nombre de usuario " + newUserDTO.getUserName() + " ya esta en uso",
                    null
            ));
        }

        User newUser = newUserDTO.DTOToUser();
        newUser.setState(true);
        newUser.setUserAlias(newUser.getUserName()+"_alias");
        person.setUser(newUser);
        Person personCreated = personRepository.save(person);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserReponseDTO(
                "Nuevo usuario: " + newUser.getUserName() + " creado con exito!",
                List.of(personCreated.getUser().entityToSecureDTO())
        ));
    }

    public ResponseEntity<UserReponseDTO> deleteUser(String personEmail) {
        Person person = personRepository.findByEmail(personEmail);
        if(person == null){
            return ResponseEntity.badRequest().body(new UserReponseDTO(
                    "La persona con email: " + personEmail + " no existe!",
                    null
            ));
        }

        User user = person.getUser();
        if(user == null){
            return ResponseEntity.badRequest().body(new UserReponseDTO(
                    "La persona con email: " + personEmail + " no tiene usuario!",
                    null
            ));
        }
        person.setUser(null);
        personRepository.save(person);
        userRepository.delete(user);

        return ResponseEntity.ok().body(new UserReponseDTO(
                "Usuario: " + user.getUserName() + " eliminado con exito!",
                List.of(user.entityToSecureDTO())
        ));
    }

    @Transactional
    public ResponseEntity<UserReponseDTO> updateUser(UpdateUserDTO updateUserDTO, EmailDTO personEmailDTO) {

        Person person = personRepository.findByEmail(personEmailDTO.getEmail());
        if(person == null){
            return ResponseEntity.badRequest().body(new UserReponseDTO(
                    "La persona con email: " + personEmailDTO.getEmail() + " no existe!",
                    null
            ));
        }

        User userToUpdate = person.getUser();
        if(userToUpdate == null){
            return ResponseEntity.badRequest().body(new UserReponseDTO(
                    "La persona con email: " + personEmailDTO.getEmail() + " no tiene usuario!",
                    null
            ));
        }

        String userName = updateUserDTO.getUserName();
        String userAlias = updateUserDTO.getUserAlias();

        if( userName != null && userName.length() > 0 && !Objects.equals(userToUpdate.getUserName(), userName)){
            userToUpdate.setUserName(userName);
        }
        if( userAlias != null && userAlias.length() > 0 && !Objects.equals(userToUpdate.getUserAlias(), userAlias)){
            userToUpdate.setUserAlias(userAlias);
        }

        return ResponseEntity.ok().body(new UserReponseDTO(
                "Usuario: " + userToUpdate.getUserName() + " modficado con exito!",
                List.of(userToUpdate.entityToSecureDTO())
        ));
    }

    public ResponseEntity<UserReponseDTO> findUserByEmail(String personEmail) {
        Person person = personRepository.findByEmail(personEmail);
        if(person == null){
            return ResponseEntity.badRequest().body(new UserReponseDTO(
                    "La persona con email: " + personEmail + " no existe!",
                    null
            ));
        }

        User user = person.getUser();
        if(user == null){
            return ResponseEntity.badRequest().body(new UserReponseDTO(
                    "La persona con email: " + personEmail + " no tiene usuario!",
                    null
            ));
        }

        return ResponseEntity.ok().body(new UserReponseDTO(
                "Usuario con email: " + personEmail + " encontrado con exito!",
                List.of(user.entityToSecureDTO())
        ));
    }

    public PersonDTO login(LoginDTO loginDTO) {
        Person person = personRepository.findByEmail(loginDTO.getEmail());
        if(person == null){
            throw new IllegalStateException("La persona con email: " + loginDTO.getEmail() + " no existe!");
        }

        User user = person.getUser();
        if(user == null){
            throw new IllegalStateException("La persona con email: " + loginDTO.getEmail() + " no tiene usuario!");
        }

        if(user.getPassword().compareTo(loginDTO.getPassword()) == 0){
            PersonDTO personDTO = PersonMapper.INSTANCE.EntityToDTO(person);
            user.setPassword(null);
            personDTO.setUser(user);
            return personDTO;
        }else{
            return null;
        }
    }

    public ResponseEntity<UserReponseDTO> findUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        if(user == null){
            return ResponseEntity.badRequest().body(new UserReponseDTO(
                    "El usuario con numero: " + userName + " no existe!",
                    null
            ));
        }

        return ResponseEntity.ok().body(new UserReponseDTO(
                "Usuario con userName: " + userName + " encontrado con exito!",
                List.of(user.entityToSecureDTO())
        ));
    }

}