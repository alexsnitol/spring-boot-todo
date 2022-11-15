package com.example.todowebflux.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@Data
@Document
public class ToDo {

    @Id
    private String id;
    private String description;
    private Boolean completed = false;
    private LocalDateTime created;
    private LocalDateTime modified;


    public ToDo() {
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
