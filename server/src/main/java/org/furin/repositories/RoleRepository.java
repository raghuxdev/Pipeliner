package org.furin.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.furin.entities.role.Role;

import java.util.UUID;

@ApplicationScoped
public class RoleRepository implements PanacheRepository<Role> {


    public Role findById(UUID id){
        return find("id",id).firstResult();
    }

    public Role findSuperAdminRole(){
        return find("key","SuperAdmin").firstResult();
    }

    public Role findByKey(String key){
        return find("key",key).firstResult();
    }



}
