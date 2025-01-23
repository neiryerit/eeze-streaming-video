package com.eeze.streaming.domain.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoBrief {

    private UUID id;
    private String title;
    private String director;
    private String mainActor;
    private int releasedYear;
    private String genre;
    private double runningTime;
}
