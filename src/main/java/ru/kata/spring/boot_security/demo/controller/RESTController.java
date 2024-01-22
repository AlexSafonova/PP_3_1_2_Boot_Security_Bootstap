package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.exception_handling.UserNotExistException;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/users")

public class RESTController {
    private final UserService userService;


    @Autowired
    public RESTController(UserService userService) {
        this.userService = userService;

    }
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {

        if (userService.getUser(id) == null) {
            throw new UserNotExistException("User with id " + id + " not found");
        }
        return userService.getUser(id);
    }
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return ResponseEntity.ok("User was added");
    }
    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return ResponseEntity.ok("User " + userDTO.getEmail() + " was updated");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id) {
        if (userService.getUser(id) == null) {
            throw new UserNotExistException("User with id " + id + " not found");
        }
        userService.deleteUser(id);
        return ResponseEntity.ok("User with id " + id + " was deleted");
    }

    @GetMapping("/user")
    public UserDTO showUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userService.getUser(user.getId());
    }


}
