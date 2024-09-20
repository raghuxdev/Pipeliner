package org.furin.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.furin.entities.permission.Permission;
import org.furin.entities.role.Role;

import java.util.List;
import java.util.UUID;


@ApplicationScoped
public class PermissionRepository implements PanacheRepository<Permission> {

    public Permission getPermissionById(UUID permissionId){
        return find("id",permissionId).firstResult();
    }

}
