package com.jscorp.webots.repository;

import com.jscorp.webots.entities.SocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * @author airat_f17@mail.ru
 */
@Repository
public interface SocialNetworkRepository extends JpaRepository <SocialNetwork, Long> {
    SocialNetwork findByName(String name);
    Optional<SocialNetwork> findById(Long id);
    List<SocialNetwork> findAll();
}
