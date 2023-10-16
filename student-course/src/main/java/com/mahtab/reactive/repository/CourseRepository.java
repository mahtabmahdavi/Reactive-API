package com.mahtab.reactive.repository;

import com.mahtab.reactive.model.Course;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CourseRepository extends ReactiveCrudRepository<Course, Long> {

    Mono<Course> findByTitle(String title);
}
