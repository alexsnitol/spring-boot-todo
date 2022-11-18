package com.example.todo.jms;

import com.example.todo.model.ToDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@Component
@Slf4j
public class ToDoProducer {

    private final JmsTemplate jmsTemplate;


    public ToDoProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    public void sendTo(String destination, ToDo toDo) {
        this.jmsTemplate.convertAndSend(destination, toDo);
        log.info("Producer >>> Message send to {}", destination);

    }

}
