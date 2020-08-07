package com.jscorp.webots.repository;

import com.jscorp.webots.entities.Profile;
import com.jscorp.webots.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author airat_f17@mail.ru
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUser(User user);
    Profile findByEmail(String email);
}
