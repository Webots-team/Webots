package com.jscorp.webots.repository;

import com.jscorp.webots.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author natalya_ezhkova@mail.ru
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findOneByName(String name);
}
