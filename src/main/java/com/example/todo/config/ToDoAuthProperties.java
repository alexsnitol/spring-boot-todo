package com.example.todo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@Data
@ConfigurationProperties(prefix = "todo.authentication")
public class ToDoAuthProperties {

    private String findByEmailUri;
    private String username;
    private String password;

}
