package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;

    }

    @GetMapping("")
    public String showAllUsers(Model model, Authentication au) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("presentUser", (User)au.getPrincipal());
        return "admin";
    }
    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam String roles) {
        String[] role = roles.split(",");
        userService.addUser(user, role);
        return "redirect:/admin";
    }
    @PostMapping(value = "/update")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam String roles) {
        String[] role = roles.split(",");
        userService.updateUser(user, role);
        return "redirect:/admin";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }



}
