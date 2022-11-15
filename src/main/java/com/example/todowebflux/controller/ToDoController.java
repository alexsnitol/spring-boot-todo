package com.example.todowebflux.controller;

import com.example.todowebflux.domain.ToDo;
import com.example.todowebflux.repository.ToDoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
//@RequestMapping("/todo")
//@RestController
public class ToDoController {

    private final ToDoRepository toDoRepository;

    public ToDoController(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }


    @GetMapping("/{id}")
    public Mono<ToDo> getToDoById(@PathVariable String id) {
        return toDoRepository.findById(id);
    }

    @GetMapping
    public Flux<ToDo> getAllToDo() {
        return toDoRepository.findAll();
    }

}
