package com.mahtab.reactive.repository;

import com.mahtab.reactive.model.entity.CoursePersonMapping;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CoursePersonMappingRepository extends ReactiveCrudRepository<CoursePersonMapping, Long> {

    @Query("SELECT EXISTS (SELECT 1 FROM course_person_mapping WHERE course = :courseId AND person = :personId)")
    Mono<Boolean> existsByCourseAndPerson(Long courseId, Long personId);
}
