package com.example.todo.repository;

import com.example.todo.model.ToDo;

import java.util.List;

public interface CommonRepository<T> {

    List<T> findAll();
    T findById(String id);
    T save(T model);
    List<T> save(List<T> modelList);
    void deleteById(String id);

}
