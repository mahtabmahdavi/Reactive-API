package com.mahtab.reactive.service;

import com.mahtab.reactive.exception.CustomException;
import com.mahtab.reactive.model.entity.Course;
import com.mahtab.reactive.model.dto.CourseDto;
import com.mahtab.reactive.repository.CourseRepository;
import com.mahtab.reactive.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final PersonRepository personRepository;
    private final PersonService personService;

    public Mono<Object> create(Course newCourse) {
        return courseRepository.findByTitleIgnoreCase(
                        newCourse.getTitle())
                .flatMap(existingStudent -> Mono.error(new CustomException("This Course is already existed")))
                .switchIfEmpty(courseRepository.save(newCourse));
    }

    public Flux<Course> readAll() {
        return courseRepository.findAll()
                .delayElements(Duration.ofSeconds(1));
    }

    public Mono<Object> readById(Long id) {
        return courseRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException("This Course was Not found")))
                .flatMap(course -> personRepository.findByCourse(id)
                        .collectList()
                        .map(people -> new CourseDto(course.getTitle(), personService.convertPersonToPersonDto(people))));
    }
}
