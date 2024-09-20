package org.furin.entities.client;


import jakarta.persistence.*;
import org.furin.entities.job.Job;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "app_connections")
@Table(name = "app_connections")
public class AppConnection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String appSecret;
    private String appName;
    private String token;

    @ManyToOne
    @JoinColumn(name = "app_id")
    private App app;

    @OneToOne(mappedBy = "appConnection")
    private Job job;


    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }


}
