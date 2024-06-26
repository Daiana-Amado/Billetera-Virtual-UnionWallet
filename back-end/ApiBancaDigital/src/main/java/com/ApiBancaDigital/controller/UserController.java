package com.ApiBancaDigital.controller;

import com.ApiBancaDigital.dto.*;
import com.ApiBancaDigital.entity.User;
import com.ApiBancaDigital.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path="/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired private  UserService userService;

    @GetMapping(path="{userNum}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary="Retorna, si existe, el usuario con el numero de usuario deseado")
    public ResponseEntity<UserReponseDTO> findUserByNum(@PathVariable("userNum") int userNum){
        return userService.findUserByNum(userNum);
    }

    @GetMapping(path="/findUserByEmail")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary="Retorna, si existe, el usuario de la persona con el email deseado")
    public ResponseEntity<UserReponseDTO> findUserByEmail(@RequestParam String personEmail){
        return userService.findUserByEmail(personEmail);
    }

    @GetMapping(path="/findUserByUserName")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary="Retorna, si existe, el usuario de la persona con el User Name deseado")
    public ResponseEntity<UserReponseDTO> findUserByUserName(@RequestParam String userName){
        return userService.findUserByUserName(userName);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary="Lista de todos los usuarios existentes")
    public ResponseEntity<UserReponseDTO> getUsers(){
        return userService.getUsers();
    }

    @PostMapping()
    @Operation(summary="Crea un nuevo usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserReponseDTO> createUser(
            @RequestBody @Valid UserDTO newUserDto,
            @RequestParam String personEmail){
        return userService.createUser(newUserDto, personEmail);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary="Elimina, si existe, el usuario con el numero de usuario deseado")
    public ResponseEntity<UserReponseDTO> deleteUser(@RequestParam String personEmail) {
        return userService.deleteUser(personEmail);
    }
    
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary="Modifica, si existe, el nombre y/o el alias del usuario con el numero de usuario deseado")
    public ResponseEntity<UserReponseDTO> updateUser(
            @RequestBody @Valid UpdateUserDTO updateUserDTO,
            @RequestParam EmailDTO personEmail){
        return userService.updateUser(updateUserDTO, personEmail);
    }

    @PostMapping(path="/login")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO login(@RequestBody @Valid LoginDTO loginDTO){
        return userService.login(loginDTO);
    }

}