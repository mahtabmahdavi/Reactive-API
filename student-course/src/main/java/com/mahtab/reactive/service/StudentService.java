package com.mahtab.reactive.service;

import com.mahtab.reactive.exception.CustomException;
import com.mahtab.reactive.model.Student;
import com.mahtab.reactive.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Mono<Object> create(Student newStudent) {
        return studentRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(
                        newStudent.getFirstName(), newStudent.getLastName())
                .flatMap(existingStudent -> Mono.error(new CustomException("This User is already existed")))
                .switchIfEmpty(studentRepository.save(newStudent));
    }

    public Flux<Student> readAll() {
        return studentRepository.findAll()
                .delayElements(Duration.ofSeconds(1));
    }
}
