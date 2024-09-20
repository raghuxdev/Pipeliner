package org.furin.repositories;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.furin.entities.mappings.UserRoleMappings;

@ApplicationScoped
public class UserRoleMappingsRepository implements PanacheRepository<UserRoleMappings> {
}
