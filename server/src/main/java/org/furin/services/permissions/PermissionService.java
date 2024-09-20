package org.furin.services.permissions;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.furin.common.Entity;
import org.furin.entities.permission.Permission;
import org.furin.entities.role.Role;
import org.furin.repositories.PermissionRepository;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class PermissionService {
    @Inject
    PermissionRepository permissionRepository;

    public List<Role>  getRolesWithPermission(UUID permissionId){
        List<Role> roles=permissionRepository.getPermissionById(permissionId).getRoles();

        return roles.isEmpty() ? null : roles ;
    }

    public List<Permission> listAll() {
        return permissionRepository.listAll();
    }



    public List<Permission> getPermissionsUsingIds(List<Entity> permissionsWithOnlyId){
        return permissionsWithOnlyId.stream().map(permission->permissionRepository.getPermissionById(permission.getId())).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
