package com.eeze.streaming.domain;

import java.time.Year;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Metadata {

    @NotBlank(message = "is missing")
    private String title;

    private String synopsis;

    @NotBlank(message = "is missing")
    private String director;

    @Valid
    @NotEmpty(message = "is missing")
    @NotNull(message = "is missing")
    private List<Performer> performers;

    @NotNull(message = "is missing")
    @Min(value = 1800, message = "must be after 1800")
    @Max(value = Year.MAX_VALUE)
    private int releasedYear;

    @NotBlank(message = "is missing")
    private String genre;

    @NotNull(message = "is missing")
    @Min(value = 1, message = "min value is 1")
    private double runningTime;
}
