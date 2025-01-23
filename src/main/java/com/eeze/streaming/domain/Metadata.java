package com.eeze.streaming.domain;

import java.time.Year;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Metadata {

    @NotNull(message = "metadata.title is missing")
    private String title;

    private String synopsis;

    @NotNull(message = "metadata.director is missing")
    private String director;

    @Valid
    @NotNull(message = "metadata.performers is missing")
    private List<Performer> performers;

    @NotNull(message = "metadata.releasedYear is missing")
    @Min(value = 1800, message = "releseadYear must be after 1800")
    @Max(value = Year.MAX_VALUE)
    private int releasedYear;

    @NotNull(message = "metadata.genre is missing")
    private String genre;

    @NotNull(message = "metadata.runningTime is missing")
    @Min(value = 1, message = "runningTime is min 1")
    private double runningTime;
}
