package org.furin.services.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.furin.entities.organization.Organization;
import org.furin.entities.role.Role;
import org.furin.entities.user.User;
import org.furin.repositories.RoleRepository;
import org.furin.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Transactional
@ApplicationScoped

public class UserService {
    Logger logger= LoggerFactory.getLogger(UserService.class);
    @Inject
    UserRepository userRepository;

    @Inject
    RoleRepository roleRepository;

    @Inject
    EntityManager entityManager;


    public User createAdminUser(User user){
        try{
            entityManager.persist(user);
            return user;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<User> getAll(){
        return userRepository.listAll();
    }


    public User getSuperAdmin(Role superAdminRole){
        User foundUser=null;
        try{
            foundUser=userRepository.findBySuperAdminRole(superAdminRole);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return foundUser;
    }


    public boolean isSuperAdminAlreadyExists(Role superAdminRole) {
        return getSuperAdmin(superAdminRole)!=null;
    }

    public Organization getOrganizationOfUser(UUID userId){
        User user=userRepository.findUserById(userId);
        return user.getOrganization();

    }

    public List<Role> getRolesOfUser(UUID userId){
        User user=userRepository.findUserById(userId);
        return user.getRoles();
    }

    public User getUserById(UUID id){
        return userRepository.findUserById(id);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }



    public List<User> getAllUsers(){
        return userRepository.listAll();
    }

    public List<Role> removeRoles(User user,List<Role> rolesToBeRemoved){
        try{

            for (Role roleToBeRemoved : rolesToBeRemoved) {
                roleToBeRemoved.getUsers().remove(user);
                user.getRoles().remove(roleToBeRemoved);
            }
            entityManager.merge(user);
            return user.getRoles();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Role> assignRolesToUser(User user,List<Role> rolesToBeAdded){
        try{
            user.getRoles().addAll(rolesToBeAdded);
            for(Role roleToBeAdded:rolesToBeAdded){
                roleToBeAdded.getUsers().add(user);
            }
            entityManager.merge(user);
           return user.getRoles();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }



}
