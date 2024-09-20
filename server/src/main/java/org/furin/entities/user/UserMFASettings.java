package org.furin.entities.user;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Table(name = "user_mfa_settings")
@Entity(name = "user_mfa_settings")
public class UserMFASettings implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private boolean isEmailMagicLinkEnabled;

    @OneToOne(mappedBy = "mfaSettings")
    private User user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isEmailMagicLinkEnabled() {
        return isEmailMagicLinkEnabled;
    }

    public void setEmailMagicLinkEnabled(boolean emailMagicLinkEnabled) {
        isEmailMagicLinkEnabled = emailMagicLinkEnabled;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
