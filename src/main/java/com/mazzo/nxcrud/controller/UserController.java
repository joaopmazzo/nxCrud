package com.mazzo.nxcrud.controller;

import com.mazzo.nxcrud.dto.request.UserDTO;
import com.mazzo.nxcrud.dto.response.MessageResponseDTO;
import com.mazzo.nxcrud.exception.UserNotFoundException;
import com.mazzo.nxcrud.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping
    public List<UserDTO> listAll() {
        return userService.listAll();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable String id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public MessageResponseDTO deleteUserById(@PathVariable String id) throws UserNotFoundException {
        return userService.deleteById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateUserById(@PathVariable String id, @RequestBody @Valid UserDTO userDTO) throws UserNotFoundException {
        return userService.updateUserById(id, userDTO);
    }

}
