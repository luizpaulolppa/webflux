package com.apirest.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.apirest.webflux.document.PlayList;
import com.apirest.webflux.repository.PlayListRepository;

import reactor.core.publisher.Flux;

@Component
public class DummyData implements CommandLineRunner {

	@Autowired
	private PlayListRepository playListRepository;

	@Override
	public void run(String... args) throws Exception {
		playListRepository.deleteAll().thenMany(
			Flux.just("API REST Spring Boot", "Deploy de uma aplicação java no IBM Cloud", "Java 8", "Github", "Spring Security", "Web Service RESTFULL", "Bean no Spring Framework")
			.map(nome -> new PlayList(null, nome)).flatMap(playListRepository::save))
			.subscribe(System.out::println);
	}
}
