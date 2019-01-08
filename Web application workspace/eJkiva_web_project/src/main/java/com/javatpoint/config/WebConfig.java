package com.javatpoint.config;

import org.jtwig.spring.JtwigViewResolver;
import org.jtwig.web.servlet.JtwigRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.javatpoint.controller.LoginController;

@Configuration
@EnableWebMvc
@Import(LoginController.class)
public class WebConfig {
    @Bean
    public ViewResolver viewResolver () {
        JtwigViewResolver viewResolver = new JtwigViewResolver();
        viewResolver.setRenderer(JtwigRenderer.defaultRenderer());
        viewResolver.setPrefix("web:/WEB-INF/jsp/");
        viewResolver.setSuffix(".twig.jsp");
        return viewResolver;
    }
}