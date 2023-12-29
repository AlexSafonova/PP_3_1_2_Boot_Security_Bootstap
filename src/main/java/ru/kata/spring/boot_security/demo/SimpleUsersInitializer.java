package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;

@Component

public class SimpleUsersInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserServiceImpl userService;
    @Autowired
    public SimpleUsersInitializer(RoleRepository roleRepository, UserServiceImpl userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }
    @Override
    public void run(String... args) throws Exception {
        roleRepository.saveAll(List.of(new Role("ROLE_USER"), new Role("ROLE_ADMIN")));
        userService.addUser(new User("admin", "admin", "admin@mail.ru", "admin"), "ROLE_ADMIN");
        userService.addUser(new User("user", "user", "user@mail.ru", "user"), "ROLE_USER");
    }
}
