package com.mazzo.nxcrud.service;

import com.mazzo.nxcrud.dto.request.UserDTO;
import com.mazzo.nxcrud.dto.response.MessageResponseDTO;
import com.mazzo.nxcrud.entity.User;
import com.mazzo.nxcrud.mapper.UserMapper;
import com.mazzo.nxcrud.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public MessageResponseDTO createUser(UserDTO userDTO) {
        User userToSave = userMapper.toModel(userDTO);
        User savedUser = userRepository.save(userToSave);
        return createMessageResponseDTO(savedUser.getId(), "User created with ID ");
    }

    public List<UserDTO> listAll() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }



    private MessageResponseDTO createMessageResponseDTO(String id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

}
