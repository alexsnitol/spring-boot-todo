package com.example.todo.repository;

import com.example.todo.model.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@DataJpaTest
class ToDoRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ToDoRepository toDoRepository;


    ToDo addTestToDo() {
        ToDo toDo = new ToDo();
        toDo.setDescription("test");
        toDo.setCompleted(false);

        entityManager.persist(toDo);

        return toDo;
    }

    @Test
    void givenToDo_whenSaveCalled_thenSaveItToDo() {
        ToDo toDo = addTestToDo();

        // test
        toDoRepository.save(toDo);
        assertThat(toDoRepository.findAll()).hasSize(1);
    }

    @Test
    void whenFindById_thenGiveToDoBySpecificId() {
        ToDo toDo = addTestToDo();

        // test
        assertThat(toDoRepository.findById(toDo.getId())).isPresent();
    }

}
