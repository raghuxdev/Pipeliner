package org.furin.services.roles;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.*;
import java.util.stream.Collectors;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.transaction.Transactional;
import org.furin.common.Entity;
import org.furin.common.EntityStatus;
import org.furin.entities.permission.Permission;
import org.furin.entities.role.Role;
import org.furin.entities.user.User;
import org.furin.repositories.PermissionRepository;
import org.furin.repositories.RoleRepository;
import org.furin.services.permissions.PermissionService;

@ApplicationScoped
@Transactional
public class RoleService {
    @Inject
    RoleRepository roleRepository;

    @Inject
    PermissionRepository permissionRepository;


    @Inject
    EntityManager entityManager;


    public List<Role> getAdminRoles(){
        return roleRepository.listAll();
    }

    public Object listAll()
    {
        return roleRepository.listAll();
    }

    public Role findById(UUID roleId) {
        return roleRepository.findById(roleId);
    }

    public Role findSuperAdminRole(){
        return roleRepository.findSuperAdminRole();
    }

    public Role findRoleByKey(String key){
        return roleRepository.findByKey(key);
    }

    public boolean isSuperAdminRoleAlreadyExists(){
        return findSuperAdminRole()!=null;
    }


    public List<Permission> assignPermissions(Role role, List<Permission> permissionsToBeAdded){
         try{
             role.getPermissions().addAll(permissionsToBeAdded);
             for(Permission permission:permissionsToBeAdded){
                 permission.getRoles().add(role);
             }
             entityManager.merge(role);
             return role.getPermissions();
         }catch (Exception e){
             System.out.println(e.getMessage());
             return Collections.emptyList();
         }

    }

    public List<Permission> removePermissions(Role role, List<Permission> permissionsToBeRemoved) {
      try{
          role.getPermissions().removeAll(permissionsToBeRemoved);
          for(Permission permission:permissionsToBeRemoved){
                  permission.getRoles().remove(role);
          }
          entityManager.merge(role);
          return role.getPermissions();
      }catch (Exception e){
          System.out.println("Error : "+e.getMessage());
          return Collections.emptyList();
      }
    }


    public Role createRole(String name,List<Permission> permissionsToBeAssigned){
        Role role=new Role();
        role.setPermissions(permissionsToBeAssigned);
        role.setEnabled(true);
        role.setKey(name);
        try{
            permissionRepository.persist(permissionsToBeAssigned);
            for(Permission permission:permissionsToBeAssigned){
                if(permission.getRoles()==null){
                    permission.setRoles(new ArrayList<>());
                }
                permission.getRoles().add(role);
            }

            roleRepository.persist(role);
            return roleRepository.findByKey(name);
        }catch (Exception e){
            System.out.println("Error while creating new Role");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Role findByKey(String key){
        return roleRepository.findByKey(key);
    }


    public boolean deleteRole(Role role){
        try{
            roleRepository.delete(role);
            return true;
        }catch (Exception e){
            System.out.println("Error while deleting Role : "+role.getKey());
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Role> getRolesUsingIds( List<Entity> roleIds){
        return roleIds.stream().map(entity -> roleRepository.findById(entity.getId())).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public List<Permission> getPermissionsOfRole(UUID id){
        return roleRepository.findById(id).getPermissions();
    }

    public List<Permission> getPermissionsOfRoles(List<Role> roles){
        List<Permission> permissions=new ArrayList<>();
         roles.forEach(role -> permissions.addAll(role.getPermissions()));
        return permissions;
    }

}
