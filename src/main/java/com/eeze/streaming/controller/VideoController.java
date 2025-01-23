package com.eeze.streaming.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.eeze.streaming.domain.dto.VideoBrief;
import com.eeze.streaming.domain.dto.VideoData;
import com.eeze.streaming.domain.dto.VideoEngagement;
import com.eeze.streaming.domain.dto.VideoReq;
import com.eeze.streaming.service.IVideoServ;
import com.eeze.streaming.util.ErrorHandler;
import com.eeze.streaming.util.QueryAction;
import com.google.gson.Gson;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private Logger log = LogManager.getLogger(VideoController.class);
    private Gson gson = new Gson();

    @Autowired
    private IVideoServ videoServ;

    @PostMapping(consumes = { "application/json; charset=UTF-8" }, produces = { "application/json; charset=UTF-8" })
    public ResponseEntity<?> addVideo(@Valid @RequestBody VideoReq videoReq, UriComponentsBuilder uriBuilder) {

        log.info("executing POST /videos");
        VideoData savedVideo = videoServ.saveVideo(videoReq);
        URI videoUri = uriBuilder
                .path("/videos/{id}")
                .buildAndExpand(savedVideo.getId())
                .toUri();
        return ResponseEntity.created(videoUri).body(savedVideo);
    }

    @PutMapping(path = "/{id}", consumes = { "application/json; charset=UTF-8" }, produces = {
            "application/json; charset=UTF-8" })
    public ResponseEntity<VideoData> updateVideo(@PathVariable UUID id, @Valid @RequestBody VideoReq videoReq) {

        log.info("executing PUT /videos/{}", id);
        VideoData video = videoServ.updateVideo(id, videoReq);
        if (video != null) {
            return ResponseEntity.ok().body(video);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}", produces = { "application/json; charset=UTF-8" })
    public ResponseEntity<VideoData> deleteVideo(@PathVariable UUID id) {

        log.info("executing DELETE /videos/{}", id);

        VideoData video = videoServ.deleteVideo(id);
        if (video != null) {
            return ResponseEntity.ok().body(video);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/{id}", produces = { "application/json; charset=UTF-8" })
    public ResponseEntity<?> getVideo(@PathVariable UUID id, @RequestParam String action) {

        log.info("executing GET /videos/{} ACTION={}", id, action);

        Object video;
        if (QueryAction.LOAD.name().equalsIgnoreCase(action)) {
            video = videoServ.loadVideo(id);

        } else if (QueryAction.PLAY.name().equalsIgnoreCase(action)) {
            video = videoServ.playVideo(id);
        } else {
            return ResponseEntity.badRequest().body(
                    ErrorHandler.getSingleMsg("Invalid value for 'action' parameter. Allowed values=LOAD,PLAY"));
        }

        if (video != null) {
            return ResponseEntity.ok().body(gson.toJson(video));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/{id}/engagements", produces = { "application/json; charset=UTF-8" })
    public ResponseEntity<VideoEngagement> getEngagement(@PathVariable UUID id) {

        log.info("executing GET /videos/{}/engagements", id);

        VideoEngagement video = videoServ.getEngagement(id);
        if (video != null) {
            return ResponseEntity.ok().body(video);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(produces = { "application/json; charset=UTF-8" })
    public ResponseEntity<List<VideoBrief>> getVideoList(@RequestParam(required = false) String director,
            @RequestParam(required = false) Integer releasedYear) {

        log.info("executing GET/ (video list). filters[director={},releasedYear={}]", director, releasedYear);

        return ResponseEntity.ok().body(videoServ.getVideoList(director, releasedYear));
    }

}
