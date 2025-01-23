package com.eeze.streaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eeze.streaming.model.Actor;

public interface IActorRepo extends JpaRepository<Actor, Long>{

}
