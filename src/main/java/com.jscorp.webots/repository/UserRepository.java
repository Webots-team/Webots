package com.jscorp.webots.repository;

import com.jscorp.webots.entities.Token;
import com.jscorp.webots.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author natalya_ezhkova@mail.ru
 */
@Repository
public  interface UserRepository extends JpaRepository<User, Long> {
    User findOneByPhone(String phone);
    User findOneByUsername(String username);
    User findOneByEmail(String email);
    Optional<User> findById(Long id);

    User findByfirstname(String firstName);
    User findByToken(Token token);


    boolean existsByPhone(String phone);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
