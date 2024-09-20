package org.furin.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.furin.entities.organization.Organization;

import java.util.UUID;

@ApplicationScoped
public class OrganizationRepository implements PanacheRepository<Organization> {

    public Organization findById(UUID id){
        return find("id",id).firstResult();
    }

    public Organization findByKey(String key){
        return find("key",key).firstResult();
    }
}
