package com.apirest.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

}
