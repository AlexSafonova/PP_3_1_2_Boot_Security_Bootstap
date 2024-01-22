package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.dto.UserDTO;

import java.util.List;

public interface UserService extends UserDetailsService {
    public void addUser(UserDTO userDTO);
    public void updateUser(UserDTO userDTO);
    public void deleteUser(Long id);
    public List<UserDTO> getAllUsers();
    public UserDTO getUser(Long id);
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
