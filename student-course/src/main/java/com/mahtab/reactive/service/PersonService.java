package com.mahtab.reactive.service;

import com.mahtab.reactive.exception.CustomException;
import com.mahtab.reactive.model.Person;
import com.mahtab.reactive.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Mono<Object> create(Person newPerson) {
        return personRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(
                        newPerson.getFirstName(), newPerson.getLastName())
                .flatMap(existingPerson -> Mono.error(new CustomException("This Person is already existed")))
                .switchIfEmpty(personRepository.save(newPerson));
    }

    public Flux<Person> readAll() {
        return personRepository.findAll()
                .delayElements(Duration.ofSeconds(1));
    }
}
