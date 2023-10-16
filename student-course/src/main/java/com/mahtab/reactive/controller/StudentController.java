package com.mahtab.reactive.controller;

import com.mahtab.reactive.exception.CustomException;
import com.mahtab.reactive.model.Student;
import com.mahtab.reactive.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("create")
    Mono<ResponseEntity<Object>> create(@RequestBody Student student) {
        return studentService.create(student)
                .map(createdStudent -> ResponseEntity.status(HttpStatus.CREATED).body(createdStudent))
                .onErrorResume(e -> {
                    if (e instanceof CustomException) {
                        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()));
                    } else {
                        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error"));
                    }
                });
    }

    @GetMapping
    Flux<Student> readAll() {
        return studentService.readAll();
    }
}
