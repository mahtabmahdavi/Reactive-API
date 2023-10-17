package com.mahtab.reactive.repository;

import com.mahtab.reactive.model.entity.Person;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {

    Mono<Person> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

    @Query("SELECT * FROM people p " +
            "JOIN course_person_mapping cpm ON p.id = cpm.person " +
            "WHERE cpm.course = :courseId")
    Flux<Person> findByCourse(@Param("courseId") Long courseId);
}
