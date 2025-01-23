package com.eeze.streaming.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eeze.streaming.model.Video;

public interface IVideoRepo extends JpaRepository<Video, UUID> {

    String FILTER_BY_QUERY_PARAMS = "SELECT v FROM Video v WHERE (:director IS NULL OR UPPER(v.director) LIKE UPPER(CONCAT('%',:director,'%'))) AND (:releasedYear IS NULL OR v.releasedYear = :releasedYear) AND UPPER(v.status)=UPPER(:status)";

    @Query(FILTER_BY_QUERY_PARAMS)
    List<Video> findByQueryParams(String director, Integer releasedYear, String status);


}
