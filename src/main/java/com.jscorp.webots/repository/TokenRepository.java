package com.jscorp.webots.repository;

import com.jscorp.webots.entities.Token;
import com.jscorp.webots.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author airat_f17@mail.ru
 */
@Repository
public interface TokenRepository extends JpaRepository <Token,Long> {
    Token findByToken(String token);
    Token findTokenByUserId(Long id);
    Token findTokenByUser(User user);


}
