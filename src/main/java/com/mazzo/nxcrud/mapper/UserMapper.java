package com.mazzo.nxcrud.mapper;

import com.mazzo.nxcrud.dto.request.UserDTO;
import com.mazzo.nxcrud.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(UserDTO userDTO);
    UserDTO toDTO(User user);

}
