package com.example.todo.repository;

import com.example.todo.exception.EntityNotFoundException;
import com.example.todo.model.ToDo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ToDoRepository implements CommonRepository<ToDo> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<ToDo> toDoRowMapper = (rs, rowNum) -> {
        ToDo toDo = new ToDo();
        toDo.setId(rs.getString("id"));
        toDo.setDescription(rs.getString("description"));
        toDo.setCompleted(rs.getBoolean("completed"));
        toDo.setCreated(rs.getTimestamp("created").toLocalDateTime());
        toDo.setModified(rs.getTimestamp("modified").toLocalDateTime());

        return toDo;
    };


    public ToDoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<ToDo> findAll() {
        return this.jdbcTemplate.query(
                "SELECT * FROM todo",
                toDoRowMapper
        );
    }

    @Override
    public ToDo findById(String id) {
        Map<String, String> params = Collections.singletonMap("id", id);

        try {
            return this.jdbcTemplate.queryForObject(
                    "SELECT * FROM todo WHERE id = :id",
                    params,
                    toDoRowMapper
            );
        } catch (Exception e) {
            throw new EntityNotFoundException(ToDo.class, id);
        }
    }

    @Override
    public ToDo save(ToDo toDo) {
        try {
            ToDo toDoInDb = findById(toDo.getId());

            toDoInDb.setDescription(toDo.getDescription());
            toDoInDb.setCompleted(toDo.getCompleted());
            toDoInDb.setModified(toDo.getModified());

            return upsert(
                    toDoInDb,
                    "UPDATE todo SET description = :description, modified = :modified, completed = :completed" +
                            " WHERE id = :id"
            );
        } catch (Exception e) {
            return upsert(
                    toDo,
                    "INSERT INTO todo(id, description, completed, created, modified)" +
                            " VALUES (:id, :description, :completed, :created, :modified)"
            );
        }
    }

    @Override
    public List<ToDo> save(List<ToDo> toDoList) {
        toDoList.forEach(this::save);
        return findAll();
    }

    private ToDo upsert(final ToDo toDo, final String sql) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", toDo.getId());
        namedParameters.put("description", toDo.getDescription());
        namedParameters.put("completed", toDo.getCompleted());
        namedParameters.put("created", Timestamp.valueOf(toDo.getCreated()));
        namedParameters.put("modified", Timestamp.valueOf(toDo.getModified()));

        this.jdbcTemplate.update(sql, namedParameters);

        return findById(toDo.getId());
    }

    @Override
    public void deleteById(String id) {
        this.jdbcTemplate.update(
                "DELETE FROM todo WHERE id = :id",
                Collections.singletonMap("id", id)
        );
    }
}
