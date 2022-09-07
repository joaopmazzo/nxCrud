package com.mazzo.nxcrud.service;

import com.mazzo.nxcrud.dto.request.UserDTO;
import com.mazzo.nxcrud.dto.response.MessageResponseDTO;
import com.mazzo.nxcrud.entity.User;
import com.mazzo.nxcrud.exception.UserNotFoundException;
import com.mazzo.nxcrud.mapper.UserMapper;
import com.mazzo.nxcrud.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public UserDTO findById(String id) throws UserNotFoundException {
        User user = verifyIfExists(id);
        return userMapper.toDTO(user);
    }

    public MessageResponseDTO deleteById(String id) throws UserNotFoundException {
        verifyIfExists(id);

        userRepository.deleteById(id);
        return createMessageResponseDTO(id, "User deleted with ID ");
    }

    public MessageResponseDTO updateUserById(String id, UserDTO userDTO) throws UserNotFoundException {
        User userData = verifyIfExists(id);

        User userToUpdate = userMapper.toModel(userDTO);

        userData.setName(userToUpdate.getName());
        userData.setDocument(userToUpdate.getDocument());
        userData.setEmail(userToUpdate.getEmail());
        userData.setCity(userToUpdate.getCity());
        userData.setState(userToUpdate.getState());

        User updatedUser = userRepository.save(userData);

        return createMessageResponseDTO(updatedUser.getId(), "User updated with ID ");
    }

    private MessageResponseDTO createMessageResponseDTO(String id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    private User verifyIfExists(String id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

}
