package com.example.todo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 Nov
 */
@Component
public class TestFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(">>> details: " + authentication.getDetails().toString());
        System.out.println(">>> credentials: " + authentication.getCredentials().toString());
        System.out.println(">>> principal: " + authentication.getPrincipal().toString());
        System.out.println(">>> name: " + authentication.getName().toString());

        chain.doFilter(request, response);
    }

}
