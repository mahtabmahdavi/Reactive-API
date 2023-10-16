package com.mahtab.reactive.service;

import com.mahtab.reactive.model.Student;
import com.mahtab.reactive.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private Mono<Student> create(Student newStudent) {
        return studentRepository.save(newStudent);
    }

    private Flux<Student> readAll() {
        return studentRepository.findAll();
    }
}
