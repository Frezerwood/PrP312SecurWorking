package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
public class RegistrationController {

//    @Autowired
//    private UserService userService;
//
//    private final PersonValidator personValidator;
//
//    @GetMapping("/registration")
//    public String registration(Model model) {
//        model.addAttribute("userForm", new User());
//
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String performRegistration(@ModelAttribute("person") @Valid Person person,
//                                      BindingResult bindingResult) {
//        personValidator.validate(person, bindingResult);
//
//        if (bindingResult.hasErrors())
//            return "/auth/registration";
//
//        registrationService.register(person);
//
//        return "redirect:/auth/login";
//    }

}
