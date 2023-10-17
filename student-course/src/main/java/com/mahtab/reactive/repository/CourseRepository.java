package com.mahtab.reactive.repository;

import com.mahtab.reactive.model.entity.Course;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CourseRepository extends ReactiveCrudRepository<Course, Long> {

    Mono<Course> findByTitleIgnoreCase(String title);
}
