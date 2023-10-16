package com.mahtab.reactive.repository;

import com.mahtab.reactive.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {
//    boolean existsByFirstNameIgnoreCaseAndLastNameIgnoreCase();
}
