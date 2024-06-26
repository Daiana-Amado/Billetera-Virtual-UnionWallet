package com.ApiBancaDigital.service;

import com.ApiBancaDigital.dto.*;
import com.ApiBancaDigital.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    public ResponseEntity<UserReponseDTO> findUserByNum(int userNum);

    public ResponseEntity<UserReponseDTO> findUserByEmail(String personEmail);

    public ResponseEntity<UserReponseDTO> findUserByUserName(String userName);

    public ResponseEntity<UserReponseDTO> getUsers();

    public ResponseEntity<UserReponseDTO> createUser(UserDTO newUserDTO, String personEmail);

    public ResponseEntity<UserReponseDTO> deleteUser(String personEmail);

    public ResponseEntity<UserReponseDTO> updateUser(UpdateUserDTO updateUserDTO, EmailDTO personEmailDTO);

    public PersonDTO login(LoginDTO loginDTO);

}