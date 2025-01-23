package com.eeze.streaming.model;

import java.util.List;
import java.util.UUID;

import com.eeze.streaming.util.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Video {

    @Id
    @Column(name = "video_id")
    @GeneratedValue
    private UUID id;
    
    private String title;
    private String synopsis;
    private String director;
    
    @Column(name = "released_year")
    private int releasedYear;

    private String genre;

    @Column(name = "running_time")
    private double runningTime; 

    private String content;

    @Enumerated(EnumType.STRING)
    private Status status;

    private int impressions;
    private int views;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "video_actor", 
        joinColumns = @JoinColumn(name = "video_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> performers;
}
