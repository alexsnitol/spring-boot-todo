package com.example.todo.controller;

import com.example.todo.model.ToDo;
import com.example.todo.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@WebMvcTest(ToDoController.class)
class ToDoControllerUnitTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ToDoRepository toDoRepository;


    @Test
    void toDoRestClientTest() throws Exception {
        ToDo toDo = new ToDo();
        toDo.setId("my-id");
        toDo.setDescription("Do Homework");
        toDo.setCompleted(true);

        given(this.toDoRepository.findById("my-id")).willReturn(Optional.of(toDo));

        mvc.perform(get("/api/todo/my-id").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("my-id"))
                .andExpect(jsonPath("$.description").value("Do Homework"))
                .andExpect(jsonPath("$.completed").value(true));
    }

}
