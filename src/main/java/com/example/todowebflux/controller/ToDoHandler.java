package com.example.todowebflux.controller;

import com.example.todowebflux.domain.ToDo;
import com.example.todowebflux.repository.ToDoRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@Component
public class ToDoHandler {

    private final ToDoRepository toDoRepository;


    public ToDoHandler(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }


    public Mono<ServerResponse> getToDoById(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<ToDo> toDo = toDoRepository.findById(id);
        return toDo
                .flatMap(t -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(t))
                ).switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> getAllToDo(ServerRequest request) {
        Flux<ToDo> toDos = this.toDoRepository.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(toDos, ToDo.class);
    }

}
