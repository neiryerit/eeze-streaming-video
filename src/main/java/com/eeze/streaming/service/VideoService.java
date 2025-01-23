package com.eeze.streaming.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eeze.streaming.domain.dto.VideoBrief;
import com.eeze.streaming.domain.dto.VideoData;
import com.eeze.streaming.domain.dto.VideoEngagement;
import com.eeze.streaming.domain.dto.VideoPlay;
import com.eeze.streaming.domain.dto.VideoReq;
import com.eeze.streaming.mapper.VideoMapper;
import com.eeze.streaming.model.Actor;
import com.eeze.streaming.model.Video;
import com.eeze.streaming.repository.IActorRepo;
import com.eeze.streaming.repository.IVideoRepo;
import com.eeze.streaming.util.Status;

@Service
public class VideoService implements IVideoServ {

    private Logger log = LogManager.getLogger(VideoService.class);

    @Autowired
    private IVideoRepo videoRepo;
    @Autowired
    private IActorRepo actorRepo;

    @Override
    public VideoData saveVideo(VideoReq videoReq) {

        log.debug("init saveVideo");
                
        List<Actor> actors = VideoMapper.INSTANCE.toActor(videoReq.getMetadata().getPerformers());
        actors = actorRepo.saveAll(actors);

        Video video = VideoMapper.INSTANCE.toEntity(videoReq);
        video.setStatus(Status.ACTIVE);

        video.setPerformers(actors);

        Video savedVideo = videoRepo.save(video);

        log.info("video {} was added",savedVideo.getId());

        return VideoMapper.INSTANCE.toDto(savedVideo);
    }

    @Override
    public VideoData updateVideo(UUID videoId, VideoReq videoReq) {

        log.debug("init updateVideo {}",videoId);

        Video video = searchVideoById(videoId);

        if (video != null) {
            video = VideoMapper.INSTANCE.updateVideo(video, videoReq);
            Video updatedVideo = videoRepo.save(video);

            log.info("video {} was updated",updatedVideo.getId());
            return VideoMapper.INSTANCE.toDto(updatedVideo);
        } else {
            log.error("video {} could not be found for updating",videoId);
            return null;
        }

    }

    @Override
    public VideoData deleteVideo(UUID videoId) {

        log.debug("init deleteVideo {}", videoId);

        Video foundVideo = searchVideoById(videoId);

        if (foundVideo != null && !foundVideo.getStatus().equals(Status.DELETE)) {
            foundVideo.setStatus(Status.DELETE);
            videoRepo.save(foundVideo);

            log.info("video {} was deleted", videoId);
            return VideoMapper.INSTANCE.toDto(foundVideo);
        } else {
            log.error("video {} could not be found for deletion",videoId);
            return null;
        }

    }

    @Override
    public VideoData loadVideo(UUID videoId) {

        log.debug("init loadVideo of ", videoId);

        Video video = searchVideoById(videoId);
        if (video != null && video.getStatus().equals(Status.ACTIVE)) {
            video.setImpressions(video.getImpressions() + 1);
            video = videoRepo.save(video);

            log.info("The video {} has been found to load", videoId);
            return VideoMapper.INSTANCE.toDto(video);
        }
        log.error("The video {} could not be found to load", videoId);
        return null;
    }

    @Override
    public VideoPlay playVideo(UUID videoId) {

        log.debug("ini playVideo of {}", videoId);

        Video video = searchVideoById(videoId);
        if (video != null && video.getStatus().equals(Status.ACTIVE)) {
            video.setViews(video.getViews() + 1);
            video = videoRepo.save(video);
            
            log.info("The video {} has been found to play", videoId);
            return VideoMapper.INSTANCE.toPlayDto(video);
        }
        log.error("The video {} could not be found to play", videoId);
        return null;
    }

    @Override
    public VideoEngagement getEngagement(UUID videoId) {

        log.debug("ini getEngagements of {}", videoId);

        Video video = searchVideoById(videoId);
        if (video != null && video.getStatus().equals(Status.ACTIVE)) {

            log.info("success engagement retrival for video:{}", videoId);
            return VideoMapper.INSTANCE.toEngagementDto(video);
        }
        log.error("video {} could not be found for getting engagements",videoId);
        return null;
    }

    @Override
    public List<VideoBrief> getVideoList(String director, Integer releasedYear) {

        log.debug("init getVideoList. FILTERS=[director:{}, releasedYear:{}]",director, releasedYear);

        List<Video> videoList = videoRepo.findByQueryParams(director, releasedYear, Status.ACTIVE.name());

        log.info("Success video list consultation");
        return VideoMapper.INSTANCE.toBriefLst(videoList);
    }

    private Video searchVideoById(UUID id) {

        Optional<Video> video = videoRepo.findById(id);
        if (video.isPresent()) {
            return video.get();
        }
        return null;
    }

}
