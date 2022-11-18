package com.example.todo.jms;

import com.example.todo.model.ToDo;
import com.example.todo.repository.ToDoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@Component
@Slf4j
public class ToDoConsumer {

    private final ToDoRepository toDoRepository;


    public ToDoConsumer(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }


    @JmsListener(destination = "${todo.jms.destination}", containerFactory = "jmsFactory")
    public void processToDo(@Valid ToDo toDo) {
        log.info("Consumer >>> {}", toDo);
        log.info("Consumer >>> ToDo created: {}", this.toDoRepository.save(toDo));
    }
}
