package org.furin.services.organization;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.furin.dto.OrganizationDto;
import org.furin.entities.organization.Organization;
import org.furin.entities.user.User;
import org.furin.repositories.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.furin.constants.CurrencyConstants.INR;


@ApplicationScoped
@Transactional
public class OrganizationService {
    Logger logger= LoggerFactory.getLogger(OrganizationService.class);
    @Inject
    OrganizationRepository organizationRepository;

    @Inject
    EntityManager entityManager;

    public Organization createOrganization(String organizationName,String key){
        Organization organization=new Organization();
        organization.setCurrency(INR);
        organization.setWalletBalance(1000);
        organization.setDisplayName(organizationName);
        organization.setKey(key);
        organization.setEnabled(true);
        organization.setUser(null);
        try{
            return organization;
        }catch (Exception e){
           logger.error("-----Organization : {} | Action : Organization Creation | Status : Failed----- ",organizationName);
           logger.error(e.getMessage());
           logger.error("---Error Log End ---");
            return null;
        }
    }

    public boolean assignAdmin(Organization organization){
        try{
            entityManager.merge(organization);
            return true;
        }catch (Exception e){
            logger.error("---- Error while Assigning Admin to Master organization  "+e.getMessage());
            return false;
        }
    }

    public List<Organization> getAll(){
        return organizationRepository.listAll();
    }

    public List<User> getUsersOfOrganization(UUID orgId){
        Organization organization=organizationRepository.findById(orgId);
        return organization.getUsers();
    }

    public Organization getOrganizationByKey(String key){
        return organizationRepository.findByKey(key);
    }
    public Organization getOrganizationById(UUID id){
        return organizationRepository.findById(id);
    }

    public List<OrganizationDto> fromOrganizations(List<Organization> organizations){
        List<OrganizationDto> organizationDtos=new ArrayList<>();
         organizations.stream().forEach(organization -> {
            organizationDtos.add(OrganizationDto.fromOrganization(organization).build());
        });
         return organizationDtos;
    }
}
