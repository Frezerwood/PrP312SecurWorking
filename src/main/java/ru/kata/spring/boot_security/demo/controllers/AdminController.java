package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userService;

    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String userList(Model model, Principal principal) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("userAuthority",userService.findByUsername(principal.getName()));
        return "/admin/index";
    }

    @GetMapping("/createUser")
    public String createUserForm(Model model, User user) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", user);
        return "/registration";
    }

    @PostMapping("/createUser")
    public String registrationUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/updateUser")
    public String updateUserForm(@RequestParam(name = "id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", user);
        return "/update_user";
    }

    @PostMapping("/updateUser")
    public String updateUser( @ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(name = "id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
