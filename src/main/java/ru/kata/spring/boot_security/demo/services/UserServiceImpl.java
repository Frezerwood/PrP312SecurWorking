package ru.kata.spring.boot_security.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;


@Service

public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByLastName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        //user.setRoles(Collections.singletonList(new Role(2L, "ROLE_USER")));

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByLastName(username);
    }
}
