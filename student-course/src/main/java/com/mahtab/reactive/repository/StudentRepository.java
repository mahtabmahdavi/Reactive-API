package com.mahtab.reactive.repository;

import com.mahtab.reactive.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {

    Mono<Student> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
}
