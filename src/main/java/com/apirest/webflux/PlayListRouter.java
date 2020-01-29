package com.apirest.webflux;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class PlayListRouter {
	
	@Bean
	public RouterFunction<ServerResponse> route(PlayListHandler handler) {
		return RouterFunctions
				.route(GET("/playLists").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
				.andRoute(GET("/playLists/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById)
				.andRoute(POST("/playLists").and(accept(MediaType.APPLICATION_JSON)), handler::save);
	}

}
