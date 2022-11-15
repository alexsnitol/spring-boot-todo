package com.example.todowebflux.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@Data
public class ToDo {

    private String id;
    private String description;
    private Boolean completed = false;
    private LocalDateTime created;
    private LocalDateTime modified;


    public ToDo() {
        this.id = UUID.randomUUID().toString();
        LocalDateTime currentDt = LocalDateTime.now();
        this.created = currentDt;
        this.modified = currentDt;
    }

    public ToDo(String description) {
        this();
        this.description = description;
    }

    public ToDo(String description, Boolean completed) {
        this();
        this.description = description;
        this.completed = completed;
    }

}
