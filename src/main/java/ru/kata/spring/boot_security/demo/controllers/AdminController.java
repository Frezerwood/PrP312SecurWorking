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

    @GetMapping(value = "")
    public String showAdminPanel(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("newUser", new User());
        return "admin-panel";
    }

//    @GetMapping("/new")
//    public String createUserForm(Model model, User user) {
//        model.addAttribute("roles", roleService.findAll());
//        model.addAttribute("user", user);
//        return "/admin/new";
//    }

    @PostMapping("/add")
    public String registrationUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

//
//    @GetMapping("/edit")
//    public String updateUserForm(@RequestParam(name = "id") Long id, Model model) {
//        User user = userService.findById(id);
//        model.addAttribute("roles", roleService.findAll());
//        model.addAttribute("user", user);
//        return "/edit";
//    }
//
//    @PostMapping("")
//    public String updateUser(@ModelAttribute("user") User user) {
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }

//    @GetMapping("/delete")
//    public String deleteUser(@RequestParam(name = "id") Long id) {
//        userService.deleteById(id);
//        return "redirect:/admin";
//    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user, @RequestParam("roles") Long[] rolesId) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
