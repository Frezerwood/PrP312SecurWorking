package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class UserPopulator implements ApplicationRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserPopulator(UserRepository userRepository,
                         RoleRepository roleRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User user = new User();
        user.setLastName("admin");
        user.setFirstName("admin");
        user.setPassword(bCryptPasswordEncoder.encode("admin"));
        userRepository.save(user);

        // Add roles for the user
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        roleRepository.save(role);

        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
    }
}

