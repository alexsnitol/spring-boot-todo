package com.example.todowebflux.repository;

import com.example.todowebflux.domain.ToDo;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@Repository
public class ToDoRepository {

    private Flux<ToDo> toDoFlux = Flux.fromIterable(Arrays.asList(
            new ToDo("Do homework"),
            new ToDo("Workout in the mornings", true),
            new ToDo("Make dinner tonight"),
            new ToDo("Clean the studio", true)
    ));


    public Mono<ToDo> findById(String id) {
        return Mono.from(toDoFlux.filter(t -> t.getId().equals(id)));
    }

    public Flux<ToDo> findAll() {
        return toDoFlux;
    }

}
