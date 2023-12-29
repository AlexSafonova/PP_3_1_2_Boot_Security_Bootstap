package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Transactional
    public void addUser(User user, String role) {
        Role role1 = roleRepository.findAll().stream().filter(r -> r.getRole().equals(role)).findFirst().orElse(null);
        assert role1 != null;
        user.setRoles(List.of(role1));
        if (userRepository.findAll().stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        userRepository.save(user);
    }


    @Transactional
    public void updateUser(User user, String role) {
        Role role1 = roleRepository.findAll().stream().filter(r -> r.getRole().equals(role)).findFirst().orElse(null);
        assert role1 != null;
        User user1 = userRepository.findById(user.getId()).orElse(null);
        List<Role> roles = user1.getRoles();
        roles.add(role1);
        user.setRoles(roles);
        userRepository.save(user);
    }


    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }


    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
}
