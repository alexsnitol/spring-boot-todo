package com.example.todo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
public class ToDo {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ToDo toDo = (ToDo) o;
        return id != null && Objects.equals(id, toDo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
