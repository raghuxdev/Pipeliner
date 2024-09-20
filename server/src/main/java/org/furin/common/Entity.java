package org.furin.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Entity {
    @JsonProperty("id")
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
