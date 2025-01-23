package com.eeze.streaming.domain.dto;

import com.eeze.streaming.domain.Metadata;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoReq {

    @NotNull(message = "metadata is missing")
    private Metadata metadata;
    @NotNull(message = "content title is missing")
    private String content;

}
