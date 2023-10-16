package com.mahtab.reactive.config;

import com.mahtab.reactive.model.Student;
import com.mahtab.reactive.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.FlywayExecutor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    @Bean
    public CommandLineRunner studentRunner(StudentService studentService) {
        return args -> {
            for (int i = 0; i < 100; i++) {
                studentService.create(
                        Student.builder()
                                .firstName("Mahtab_" + i)
                                .lastName("Mahdavi_" + i)
                                .average(17.0 + 0.1 * i)
                                .age(25)
                                .build()).subscribe();
            }
        };
    }
}
