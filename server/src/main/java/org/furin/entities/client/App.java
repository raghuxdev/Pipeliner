package org.furin.entities.client;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Table(name = "apps")
@Entity(name = "apps")
public class App implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String appName;
    private Boolean isEnabled;
    private Boolean allowCustomConnections;
    private Boolean isShared;
    private String key;

    @OneToMany(mappedBy = "app")
    private List<AppConnection> connections;

    public List<AppConnection> getConnections() {
        return connections;
    }

    public void setConnections(List<AppConnection> connections) {
        this.connections = connections;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Boolean getAllowCustomConnections() {
        return allowCustomConnections;
    }

    public void setAllowCustomConnections(Boolean allowCustomConnections) {
        this.allowCustomConnections = allowCustomConnections;
    }

    public Boolean getShared() {
        return isShared;
    }

    public void setShared(Boolean shared) {
        isShared = shared;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
