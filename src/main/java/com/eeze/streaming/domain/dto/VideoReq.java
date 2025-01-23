package com.eeze.streaming.domain.dto;

import com.eeze.streaming.domain.Metadata;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoReq {

    @Valid
    @NotNull(message = "is missing")
    private Metadata metadata;
    @NotBlank(message = "is missing")
    private String content;

}
