package com.ApiBancaDigital.mapper;

import com.ApiBancaDigital.dto.UserDTO;
import com.ApiBancaDigital.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO entityToDTO(User user);

    User DTOToUser(UserDTO userDto);
}
