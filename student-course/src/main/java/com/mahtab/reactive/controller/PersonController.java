package com.mahtab.reactive.controller;

import com.mahtab.reactive.exception.CustomException;
import com.mahtab.reactive.model.entity.Person;
import com.mahtab.reactive.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/people")
public class PersonController {

    private final PersonService personService;

    @PostMapping("create")
    Mono<ResponseEntity<Object>> create(@RequestBody Person person) {
        return personService.create(person)
                .map(createdPerson -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(createdPerson))
                .onErrorResume(e -> {
                    if (e instanceof CustomException) {
                        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(e.getMessage()));
                    } else {
                        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Internal Server Error"));
                    }
                });
    }

    @GetMapping
    Flux<Person> readAll() {
        return personService.readAll();
    }
}
