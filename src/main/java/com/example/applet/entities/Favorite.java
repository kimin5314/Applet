package com.example.applet.entities;

import jakarta.persistence.*;

@Entity
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long resourceId;

    public Favorite() {
    }

    public Favorite(Long userId, Long resourceId) {
        this.userId = userId;
        this.resourceId = resourceId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
