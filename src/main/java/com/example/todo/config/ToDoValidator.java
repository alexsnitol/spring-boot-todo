package com.example.todo.config;

import com.example.todo.model.ToDo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
public class ToDoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(ToDo.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ToDo toDo = (ToDo) target;

        if (toDo == null) {
            errors.reject(null, "ToDo cannot be null");
        } else {
            if (toDo.getDescription() == null || toDo.getDescription().isEmpty()) {
                errors.rejectValue("description", null,
                        "Description cannot be null or empty");
            }
        }
    }

}
