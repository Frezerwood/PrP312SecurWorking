package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

<<<<<<< HEAD
    // Optional<User> findByFirstName(String s);

    User findByFirstName(String username);

=======

    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findByFirstName(String s);

    //User findUserByUsername(String firstName);
    //User findByUsername(String s);
>>>>>>> prp312secur/master
}
