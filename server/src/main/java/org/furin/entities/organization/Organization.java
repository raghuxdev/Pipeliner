package org.furin.entities.organization;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.furin.entities.user.User;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Table(name = "organizations")
@Entity(name = "organizations")
public class Organization implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    private boolean isEnabled;

    private String key;

    private String displayName;

    private int walletBalance;

    private String currency;

    @JsonProperty("user")
    @OneToOne(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"roles","organization"})
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "organization",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "organization",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<OrganizationSettings> organizationSettings;

    public List<User> getUsers() {
        return users;
    }

    private boolean mfaEnabled;


    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrganizationSettings> getOrganizationSettings() {
        return organizationSettings;
    }

    public void setOrganizationSettings(List<OrganizationSettings> organizationSettings) {
        this.organizationSettings = organizationSettings;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(int walletBalance) {
        this.walletBalance = walletBalance;
    }

    public boolean isMfaEnabled() {
        return mfaEnabled;
    }

    public void setMfaEnabled(boolean mfaEnabled) {
        this.mfaEnabled = mfaEnabled;
    }
}
