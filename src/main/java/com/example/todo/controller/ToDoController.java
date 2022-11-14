package com.example.todo.controller;

import com.example.todo.model.ToDo;
import com.example.todo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoRepository toDoRepository;


    @GetMapping
    public List<ToDo> getAll() {
        return toDoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getById(@PathVariable String id) {
        Optional<ToDo> result = toDoRepository.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ToDo> add(@Valid @RequestBody ToDo toDo) {
        return new ResponseEntity<>(toDoRepository.save(toDo), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        toDoRepository.deleteById(id);
        return ResponseEntity.ok("Entity has been deleted");
    }

    @PutMapping
    public ToDo updateById(@Valid @RequestBody ToDo toDo) {
        return toDoRepository.save(toDo);
    }

}
