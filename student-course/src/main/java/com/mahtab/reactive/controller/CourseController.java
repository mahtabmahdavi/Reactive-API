package com.mahtab.reactive.controller;

import com.mahtab.reactive.exception.CustomException;
import com.mahtab.reactive.model.Course;
import com.mahtab.reactive.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("create")
    Mono<ResponseEntity<Object>> create(@RequestBody Course course) {
        return courseService.create(course)
                .map(createdStudent -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(createdStudent))
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
    Flux<Course> readAll() {
        return courseService.readAll();
    }
}
