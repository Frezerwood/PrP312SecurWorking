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

import java.util.Collections;

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

        userMaker("admin","admin","ROLE_ADMIN");
        userMaker("user","user","ROLE_USER");
    }

    private void userMaker (String name, String pass, String roleName){
        User user = new User();
        user.setLastName(name);
        user.setPassword(bCryptPasswordEncoder.encode(pass));
        userRepository.save(user);

        Role role = new Role();
        role.setName(roleName);
        roleRepository.save(role);

        user.setRoles(Collections.singleton(role));
        userRepository.save(user);
    }
}

