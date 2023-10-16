package com.mahtab.reactive.controller;

import com.mahtab.reactive.model.Student;
import com.mahtab.reactive.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("create")
    Mono<Student> create(@RequestBody Student student) {
        return studentService.create(student);
    }

    @GetMapping
    Flux<Student> readAll() {
        return studentService.readAll();
    }
}
