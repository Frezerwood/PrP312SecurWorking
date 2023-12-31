package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping("/createUser")
    public String createUserForm(Model model, User user) {
        model.addAttribute("roles", roleRepository.findAll());
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
        model.addAttribute("roles", roleRepository.findAll());
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
