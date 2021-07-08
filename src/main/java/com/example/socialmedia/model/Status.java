package com.example.socialmedia.model;

import com.example.socialmedia.model.abstruct.DateAudit;
import com.example.socialmedia.model.enumeration.PrivacyEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Status extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Status details can not be empty")
    private String details;

    @Enumerated(EnumType.STRING)
    private PrivacyEnum privacy;

    @ManyToOne(optional = false)
    private Location location;

    @ManyToOne(optional = false)
    private User user;

    public Status() {
    }

    public Status(String details, PrivacyEnum privacy, Location location) {
        this.details = details;
        this.privacy = privacy;
        this.location = location;
    }

    public Status(String details, PrivacyEnum privacy, Location location, User user) {
        this.details = details;
        this.privacy = privacy;
        this.location = location;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public PrivacyEnum getPrivacy() {
        return privacy;
    }

    public void setPrivacy(PrivacyEnum privacy) {
        this.privacy = privacy;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Status{");
        sb.append("id=").append(id);
        sb.append(", details='").append(details).append('\'');
        sb.append(", privacy=").append(privacy);
        sb.append(", location=").append(location);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
