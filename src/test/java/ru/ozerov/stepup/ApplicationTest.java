package ru.ozerov.stepup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.ozerov.stepup"})
@EntityScan(basePackages = {"ru.ozerov.stepup"})
public class ApplicationTest {

    static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class, args);
    }
}
