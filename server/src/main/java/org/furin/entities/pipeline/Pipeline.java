package org.furin.entities.pipeline;

import jakarta.persistence.*;
import org.furin.entities.job.Job;
import org.furin.entities.user.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "pipelines")
@Entity(name = "pipelines")
public class Pipeline implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String pipelineName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "pipeline")
    private List<Job> jobs;

    @OneToMany(mappedBy = "pipeline")
    private List<PipelineExecution> pipelineExecutions;


    public List<PipelineExecution> getPipelineExecutions() {
        return pipelineExecutions;
    }

    public void setPipelineExecutions(List<PipelineExecution> pipelineExecutions) {
        this.pipelineExecutions = pipelineExecutions;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    private boolean isActive;
   private LocalDateTime publishedAt;

   private LocalDateTime createdAt;

   private LocalDateTime updatedAt;

   private LocalDateTime deletedAt;

   private String remoteWebHookId;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getRemoteWebHookId() {
        return remoteWebHookId;
    }

    public void setRemoteWebHookId(String remoteWebHookId) {
        this.remoteWebHookId = remoteWebHookId;
    }
}
