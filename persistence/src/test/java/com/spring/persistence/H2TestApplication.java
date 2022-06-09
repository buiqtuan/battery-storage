package com.spring.persistence;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Log4j2
@EntityScan("com.spring.persistence.entity")
@EnableJpaRepositories("com.spring.persistence.repository")
public class H2TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(H2TestApplication.class, args);
    }
}
