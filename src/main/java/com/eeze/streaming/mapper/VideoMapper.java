package com.eeze.streaming.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.eeze.streaming.domain.Performer;
import com.eeze.streaming.domain.dto.VideoBrief;
import com.eeze.streaming.domain.dto.VideoData;
import com.eeze.streaming.domain.dto.VideoEngagement;
import com.eeze.streaming.domain.dto.VideoPlay;
import com.eeze.streaming.domain.dto.VideoReq;
import com.eeze.streaming.model.Actor;
import com.eeze.streaming.model.Video;

@Mapper 
public interface VideoMapper {

    VideoMapper INSTANCE = Mappers.getMapper(VideoMapper.class);
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "metadata.title")
    @Mapping(source = "synopsis", target = "metadata.synopsis")
    @Mapping(source = "director", target = "metadata.director")
    @Mapping(source = "releasedYear", target = "metadata.releasedYear")
    @Mapping(source = "genre", target = "metadata.genre")
    @Mapping(source = "runningTime", target = "metadata.runningTime")
    @Mapping(source = "performers", target = "metadata.performers")
    @Mapping(source = "content", target = "content")
    VideoData toDto(Video video);

    @Mapping(source = "content", target = "content")
    VideoPlay toPlayDto(Video video);

    @Mapping(source = "impressions", target = "impressions")
    @Mapping(source = "views", target = "views")
    VideoEngagement toEngagementDto(Video video);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "metadata.title", target = "title")
    @Mapping(source = "metadata.synopsis", target = "synopsis")
    @Mapping(source = "metadata.director", target = "director")
    @Mapping(source = "metadata.releasedYear", target = "releasedYear")
    @Mapping(source = "metadata.genre", target = "genre")
    @Mapping(source = "metadata.runningTime", target = "runningTime")
    @Mapping(source = "metadata.performers", target = "performers")
    @Mapping(source = "content", target = "content")
    Video toEntity(VideoData videoDTO);

    @Mapping(source = "metadata.title", target = "title")
    @Mapping(source = "metadata.synopsis", target = "synopsis")
    @Mapping(source = "metadata.director", target = "director")
    @Mapping(source = "metadata.releasedYear", target = "releasedYear")
    @Mapping(source = "metadata.genre", target = "genre")
    @Mapping(source = "metadata.runningTime", target = "runningTime")
    @Mapping(source = "content", target = "content")
    Video toEntity(VideoReq videoReq);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "character", target = "character")
    @Mapping(source = "role", target = "role")
    List<Actor> toActor(List<Performer> performer);

    @Mapping(source = "videoReq.metadata.title", target = "title")
    @Mapping(source = "videoReq.metadata.synopsis", target = "synopsis")
    @Mapping(source = "videoReq.metadata.director", target = "director")
    @Mapping(source = "videoReq.metadata.releasedYear", target = "releasedYear")
    @Mapping(source = "videoReq.metadata.genre", target = "genre")
    @Mapping(source = "videoReq.metadata.runningTime", target = "runningTime")
    @Mapping(source = "videoReq.metadata.performers", target = "performers")
    @Mapping(source = "videoReq.content", target = "content")
    Video updateVideo(@MappingTarget Video video, VideoReq videoReq);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "director", target = "director")
    @Mapping(target = "mainActor", expression = "java(performers.stream().filter(actor->actor.getRole.equalsIgnoreCase(ActorRole.LEAD)).findFirst().getName() )")
    @Mapping(source = "genre", target = "genre")
    @Mapping(source = "runningTime", target = "runningTime")
    List<VideoBrief> toBriefLst(List<Video> videoLst);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "metadata.title")
    @Mapping(source = "synopsis", target = "metadata.synopsis")
    @Mapping(source = "director", target = "metadata.director")
    @Mapping(source = "releasedYear", target = "metadata.releasedYear")
    @Mapping(source = "genre", target = "metadata.genre")
    @Mapping(source = "runningTime", target = "metadata.runningTime")
    @Mapping(source = "performers", target = "metadata.performers")
    @Mapping(source = "content", target = "content")
    List<VideoData> toDtoLst(List<Video> video);
}
