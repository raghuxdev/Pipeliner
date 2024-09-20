package org.furin.entities.job;


import jakarta.persistence.*;
import org.furin.entities.client.AppConnection;
import org.furin.entities.pipeline.Pipeline;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "jobs")
@Entity(name = "jobs")
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String key;

    private String appKey;

    private String type;

    @OneToOne
    @JoinColumn(name = "connection_id")
    private AppConnection appConnection;

    @ManyToOne
    @JoinColumn(name = "pipeline_id")
    private Pipeline pipeline;

    @OneToMany(mappedBy = "job")
    private List<JobExecution> jobExecutions;



    private String parameters;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    private int position;

    private String status;

    private String webhookPath;

    public Pipeline getPipeline() {
        return pipeline;
    }


    public List<JobExecution> getJobExecutions() {
        return jobExecutions;
    }

    public void setJobExecutions(List<JobExecution> jobExecutions) {
        this.jobExecutions = jobExecutions;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AppConnection getAppConnection() {
        return appConnection;
    }

    public void setAppConnection(AppConnection appConnection) {
        this.appConnection = appConnection;
    }

    public String getConfiguredParameters() {
        return parameters;
    }

    public void setConfiguredParameters(String parameters) {
        this.parameters = parameters;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWebhookPath() {
        return webhookPath;
    }

    public void setWebhookPath(String webhookPath) {
        this.webhookPath = webhookPath;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
}
