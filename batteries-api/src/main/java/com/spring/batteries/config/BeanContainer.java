package com.spring.batteries.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanContainer {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
