package com.example.todo.exception;

public class EntityNotFoundException extends RuntimeException {

    public <T> EntityNotFoundException(Class<T> modelClass, String id) {
        super(modelClass.getSimpleName() + " with id " + id + " not found");
    }

}
