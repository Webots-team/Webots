package com.jscorp.webots.repository;

import com.jscorp.webots.entities.Profile;
import com.jscorp.webots.entities.SocialLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author airat_f17@mail.ru
 */
@Repository
public interface SocialLinkRepository extends JpaRepository<SocialLink, Long> {
    public SocialLink findByProfile(Profile profile);
}
