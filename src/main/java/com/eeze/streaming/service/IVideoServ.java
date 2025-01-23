package com.eeze.streaming.service;

import java.util.List;
import java.util.UUID;

import com.eeze.streaming.domain.dto.VideoBrief;
import com.eeze.streaming.domain.dto.VideoData;
import com.eeze.streaming.domain.dto.VideoEngagement;
import com.eeze.streaming.domain.dto.VideoPlay;
import com.eeze.streaming.domain.dto.VideoReq;

public interface IVideoServ {

    public VideoData saveVideo(VideoReq videoReq);
    public VideoData updateVideo(UUID videoId, VideoReq videoReq);
    public VideoData deleteVideo(UUID videoId);
    public VideoData loadVideo(UUID videoId);
    public VideoPlay playVideo(UUID videoId);
    public VideoEngagement getEngagement(UUID videoId);
    public List<VideoBrief> getVideoList(String director, Integer releasedYear);
}
