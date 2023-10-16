package com.mahtab.reactive.service;

import com.mahtab.reactive.exception.CustomException;
import com.mahtab.reactive.model.Course;
import com.mahtab.reactive.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

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
}
