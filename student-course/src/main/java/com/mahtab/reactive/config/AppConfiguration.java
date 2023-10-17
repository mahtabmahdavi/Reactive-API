package com.mahtab.reactive.config;

import com.mahtab.reactive.model.entity.Course;
import com.mahtab.reactive.model.entity.CoursePersonMapping;
import com.mahtab.reactive.model.entity.Person;
import com.mahtab.reactive.repository.CoursePersonMappingRepository;
import com.mahtab.reactive.service.CourseService;
import com.mahtab.reactive.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    private final CoursePersonMappingRepository mappingRepository;

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
            coursePersonRunner();
        };
    }

    @Bean
    public CommandLineRunner coursePersonRunner() {
        return args -> {
            for (Long i = 1L; i < 10; i++) {
                mappingRepository.save(
                        CoursePersonMapping.builder()
                                .course(i)
                                .person(i * 2)
                                .build()).subscribe();
                mappingRepository.save(
                        CoursePersonMapping.builder()
                                .course(i)
                                .person(i * 3)
                                .build()).subscribe();
            }
        };
    }
}
