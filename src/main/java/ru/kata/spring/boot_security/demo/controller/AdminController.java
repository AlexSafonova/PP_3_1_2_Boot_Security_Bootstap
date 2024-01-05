package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    public String showAllUsers(Model model, Authentication au) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("presentUser", (User)au.getPrincipal());
        return "admin";
    }
    @PostMapping(value = "/add")
    public String addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password,
                          @RequestParam String role) {
        User user = new User(firstName, lastName, email, password);

        if (userService.getAllUsers().stream().anyMatch(user1 -> user1.getEmail().equals(email))) {
            return "redirect:/admin";
        }
        userService.addUser(user, role);

        return "redirect:/admin";
    }
    @PostMapping(value = "/update")
    public String updateUser(@RequestParam Long id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email,  @RequestParam String role) {
        User user = userService.getUser(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        if (userService.getAllUsers().stream().noneMatch(user1 -> user1.getId().equals(id))) {
            return "redirect:/admin";
        }
        userService.updateUser(user, role);
        return "redirect:/admin";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }



}
