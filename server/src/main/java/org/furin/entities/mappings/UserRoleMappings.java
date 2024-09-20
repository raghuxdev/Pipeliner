package org.furin.entities.mappings;


import jakarta.persistence.*;
import org.furin.entities.user.User;
import org.furin.entities.role.Role;

import java.util.UUID;

@Table(name = "user_role_mappings")
@Entity(name = "user_role_mappings")
public class UserRoleMappings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
