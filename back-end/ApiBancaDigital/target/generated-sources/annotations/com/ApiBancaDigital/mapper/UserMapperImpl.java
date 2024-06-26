package com.ApiBancaDigital.mapper;

import com.ApiBancaDigital.dto.UserDTO;
import com.ApiBancaDigital.entity.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-16T14:47:42-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO entityToDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setNum_user( user.getNum_user() );
        userDTO.setAdmin( user.isAdmin() );
        userDTO.setUserName( user.getUserName() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setUserAlias( user.getUserAlias() );
        userDTO.setToken( user.getToken() );
        userDTO.setFailAttemps( user.getFailAttemps() );
        userDTO.setState( user.isState() );
        userDTO.setCreationDate( user.getCreationDate() );
        userDTO.setLastModify( user.getLastModify() );

        return userDTO;
    }

    @Override
    public User DTOToUser(UserDTO userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.num_user( userDto.getNum_user() );
        user.userName( userDto.getUserName() );
        user.password( userDto.getPassword() );
        user.userAlias( userDto.getUserAlias() );
        user.token( userDto.getToken() );
        user.failAttemps( userDto.getFailAttemps() );
        user.state( userDto.isState() );
        user.creationDate( userDto.getCreationDate() );
        user.lastModify( userDto.getLastModify() );

        return user.build();
    }
}
