package com.apirest.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.apirest.webflux.document.PlayList;

public interface PlayListRepository extends ReactiveMongoRepository<PlayList, String> {

}
