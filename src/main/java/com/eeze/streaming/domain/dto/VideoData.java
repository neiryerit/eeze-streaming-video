package com.eeze.streaming.domain.dto;

import java.util.UUID;

import com.eeze.streaming.domain.Metadata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoData {

    private UUID id;
    private Metadata metadata;
    private String content;
}
