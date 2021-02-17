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
    private Long session_id;
    private String session_name;
    private String session_description;
    private Integer session_length;

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

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public String getSession_description() {
        return session_description;
    }

    public void setSession_description(String session_description) {
        this.session_description = session_description;
    }

    public Integer getSession_length() {
        return session_length;
    }

    public void setSession_length(Integer session_length) {
        this.session_length = session_length;
    }
}
