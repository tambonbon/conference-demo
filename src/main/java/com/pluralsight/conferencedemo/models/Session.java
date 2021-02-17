package com.pluralsight.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sessions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Hibernate adds few stub methods to handle lazy loading and eager loading of relational data
public class Session { // this will be one row of our data
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // how PK get populated
    // add variables matching the name in database
    // so that i won't use annotations to bind names
    @Column(name = "session_id")
    private Long sessionId;

    @Column(name = "session_name")
    private String sessionName;

    @Column(name = "session_description")
    private String sessionDescription;

    @Column(name = "session_length")
    private Integer sessionLength;

    // add Speaker entity to make a relationship
    @ManyToMany
    @JoinTable(
            name = "session_speakers",
            joinColumns = @JoinColumn(name = "session_id"), // foreign key column
            inverseJoinColumns = @JoinColumn(name = "speaker_id") // foreign key column
    )
    private List<Speaker> speakers;

    // add default constructor to entity
    // which will help with serializaition/deserialization
    public Session(){ }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionDescription() {
        return sessionDescription;
    }

    public void setSessionDescription(String sessionDescription) {
        this.sessionDescription = sessionDescription;
    }

    public Integer getSessionLength() {
        return sessionLength;
    }

    public void setSessionLength(Integer sessionLength) {
        this.sessionLength = sessionLength;
    }
}
