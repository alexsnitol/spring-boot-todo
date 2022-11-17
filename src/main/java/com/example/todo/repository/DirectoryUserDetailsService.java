package com.example.todo.repository;

import com.example.todo.config.ToDoAuthProperties;
import com.example.todo.dto.PersonDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@Service
public class DirectoryUserDetailsService implements UserDetailsService {

    private final RestTemplate restTemplate;
    private final ToDoAuthProperties authProperties;


    public DirectoryUserDetailsService(
            RestTemplateBuilder restTemplateBuilder,
            ToDoAuthProperties authProperties
    ) {
        this.restTemplate = restTemplateBuilder.basicAuthentication(
                authProperties.getUsername(),
                authProperties.getPassword()
        ).build();
        this.authProperties = authProperties;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            RequestEntity<Void> request = RequestEntity
                    .get(URI.create(authProperties.getFindByEmailUri() + username))
                    .accept(MediaType.APPLICATION_JSON)
                    .build();

            ResponseEntity<PersonDto> response = restTemplate.exchange(
                    request,
                    new ParameterizedTypeReference<PersonDto>() {}
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                PersonDto person = response.getBody();
                return User
                        .withUsername(person.getEmail())
                        .password(person.getPassword())
                        .roles(person.getRole())
                        .accountLocked(!person.isEnabled())
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new UsernameNotFoundException("User with username " + authProperties.getUsername() + " not found");
    }


}
