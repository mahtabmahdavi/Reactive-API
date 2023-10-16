package com.mahtab.reactive.repository;

import com.mahtab.reactive.model.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {

    Mono<Person> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
}
