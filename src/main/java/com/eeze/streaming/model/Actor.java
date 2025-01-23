package com.eeze.streaming.model;

import java.util.HashSet;
import java.util.Set;

import com.eeze.streaming.util.ActorRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Actor {

    @Id
    @Column(name = "actor_id")
    @GeneratedValue
    private long id;
    private String name;
    private String character;
    private ActorRole role;

    @ManyToMany(mappedBy = "performers")
    private Set<Video> videos;// = new HashSet<>();

}
