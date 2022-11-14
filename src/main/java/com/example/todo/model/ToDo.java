package com.example.todo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RedisHash
public class ToDo {

    @Id
    private String id;
    @NotNull
    @NotBlank
    private String description;
    private Boolean completed = false;
    private LocalDateTime created;
    private LocalDateTime modified;


    public ToDo() {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.created = localDateTime;
        this.modified = localDateTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        ToDo toDo = (ToDo) o;
        return id != null && Objects.equals(id, toDo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
