package com.apirest.webflux;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.apirest.webflux.document.PlayList;
import com.apirest.webflux.service.PlayListService;

import reactor.core.publisher.Mono;

//@Component
public class PlayListHandler {

	@Autowired
	private PlayListService playListService;

	public Mono<ServerResponse> findAll(ServerRequest request) {
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(playListService.findAll(), PlayList.class);
	}

	public Mono<ServerResponse> findById(ServerRequest request) {
		String id = request.pathVariable("id");
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(playListService.findById(id), PlayList.class);
	}
	
	public Mono<ServerResponse> save(ServerRequest request) {
		Mono<PlayList> playList = request.bodyToMono(PlayList.class);
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fromPublisher(playList.flatMap(playListService::save), PlayList.class));
	}

}
