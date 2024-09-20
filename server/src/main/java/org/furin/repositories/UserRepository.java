package org.furin.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.furin.entities.role.Role;
import org.furin.entities.user.User;

import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public User findByEmail(String email){
        return find("email" ,email).firstResult();
    }

    public User findBySuperAdminRole(Role role) {
        return find("role",role).firstResult();
    }

    public User findUserById(UUID id){
        return find("id",id).firstResult();
    }
}
