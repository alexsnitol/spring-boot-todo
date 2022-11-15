package com.example.todowebflux.repository;

import com.example.todowebflux.domain.ToDo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@Repository
public interface ToDoRepository extends ReactiveMongoRepository<ToDo, String> {

}
