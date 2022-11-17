package com.example.todo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDto {

    private String email;
    private String password;
    private String role;
    private boolean enabled;

}
