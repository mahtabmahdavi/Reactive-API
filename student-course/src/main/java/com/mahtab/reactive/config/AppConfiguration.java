package com.mahtab.reactive.config;

import com.mahtab.reactive.model.Course;
import com.mahtab.reactive.model.Person;
import com.mahtab.reactive.service.CourseService;
import com.mahtab.reactive.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    @Bean
    public CommandLineRunner personRunner(PersonService personService) {
        return args -> {
            for (int i = 0; i < 100; i++) {
                personService.create(
                        Person.builder()
                                .firstName("Mahtab_" + i)
                                .lastName("Mahdavi_" + i)
                                .age(25)
                                .build()).subscribe();
            }
        };
    }

    @Bean
    public CommandLineRunner courseRunner(CourseService courseService) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                courseService.create(
                        Course.builder()
                                .title("math_" + i)
                                .build()).subscribe();
            }
        };
    }
}
