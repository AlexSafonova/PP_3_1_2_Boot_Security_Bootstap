package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;
import java.util.Set;

@Component
public class SimpleUsersInitializer implements CommandLineRunner {
    private final UserServiceImpl userService;
    private final RoleRepository roleRepository;
    @Autowired
    public SimpleUsersInitializer(UserServiceImpl userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        roleRepository.saveAll(Set.of(new Role("ROLE_USER"), new Role("ROLE_ADMIN")));
        userService.addUser(new UserDTO("user", "userov", "user@mail.ru", "user", List.of("ROLE_USER")));
        userService.addUser(new UserDTO("admin", "adminov", "admin@mail.ru", "admin" , List.of("ROLE_ADMIN")));
    }
}