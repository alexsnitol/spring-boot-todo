package com.example.todo.repository;

import com.example.todo.model.ToDo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ToDoRepository extends PagingAndSortingRepository<ToDo, String> {

}
