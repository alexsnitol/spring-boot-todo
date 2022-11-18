package com.example.todo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@ConfigurationProperties(prefix = "todo.jms")
@Data
public class ToDoProperties {

    private String destination;

}
