package org.furin.entities.organization;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;
@Entity(name = "organization_settings")
@Table(name = "organization_settings")
public class OrganizationSettings implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    private String key;

    private String value;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
