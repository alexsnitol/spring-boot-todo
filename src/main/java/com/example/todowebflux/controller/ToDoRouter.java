package com.example.todowebflux.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@Configuration
public class ToDoRouter {

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(ToDoHandler toDoHandler) {
        return route(GET("/todo/{id}").and(accept(MediaType.APPLICATION_JSON)), toDoHandler::getToDoById)
                .andRoute(GET("/todo").and(accept(MediaType.APPLICATION_JSON)), toDoHandler::getAllToDo)
                .andRoute(POST("/todo").and(accept(MediaType.APPLICATION_JSON)), toDoHandler::addToDo);
    }

}
