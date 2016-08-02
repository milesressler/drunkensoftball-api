package com.drunkensoftball.api.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "created", updatable = false)
    private Calendar created;

    @Column(name = "updated")
    private Calendar updated;

    @JsonIgnore
    @Column(name = "removed", nullable=false)
    private boolean removed;

    @PrePersist
    protected void onCreate() {
        this.created = Calendar.getInstance();
        this.uuid = UUID.randomUUID().toString();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated = Calendar.getInstance();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreated() {
        return created;
    }

    public Calendar getUpdated() {
        return updated;
    }

    public void setUpdated(Calendar updated) {
        this.updated = updated;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public String getUuid() {
        return uuid;
    }
}
