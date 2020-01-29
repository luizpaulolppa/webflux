package com.apirest.webflux.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.webflux.document.PlayList;
import com.apirest.webflux.service.PlayListService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping("/playLists")
public class PlayListController {
	
	@Autowired
	private PlayListService playListService;
	
	@GetMapping
	public Flux<PlayList> getPlaylists() {
		return playListService.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<PlayList> getplayListById(@PathVariable(name = "id", required = true) String id) {
		return playListService.findById(id);
	}
	
	@PostMapping
	public Mono<PlayList> savePlayList(@RequestBody PlayList playList) {
		return playListService.save(playList);
	}
	
	@GetMapping(value="/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, PlayList>> getPlaylistByWebflux(){
		System.out.println("---Start get Playlists by WEBFLUX--- " + LocalDateTime.now());
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
        Flux<PlayList> playlistFlux = playListService.findAll();
        return Flux.zip(interval, playlistFlux);
	}
	
	@GetMapping(value="/flux")
	public Flux<PlayList> getPlaylistByMvc() throws InterruptedException {
		System.out.println("--- START GET PLAYLIST --- " + LocalDateTime.now());
		Flux<PlayList> playListFlux = playListService.findAll();
		TimeUnit.SECONDS.sleep(5);
		return playListFlux;
	}

}
