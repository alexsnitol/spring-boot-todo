package com.example.todo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ErrorHandler;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@Slf4j
public class ToDoErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        log.warn("ToDo error...");
        log.error(t.getCause().getMessage());
    }

}
