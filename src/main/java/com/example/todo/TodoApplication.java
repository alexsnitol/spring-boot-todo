package com.example.todo;

import com.example.todo.jms.ToDoProducer;
import com.example.todo.model.ToDo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			@Value("${todo.jms.destination}") String destination,
			ToDoProducer producer
	) {
		return args -> {
			ToDo toDo = new ToDo();
			toDo.setDescription("Workout tomorrow morning!");

			producer.sendTo(destination, toDo);
		};
	}

}
