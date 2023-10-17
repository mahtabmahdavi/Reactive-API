package com.mahtab.reactive.repository;

import com.mahtab.reactive.model.entity.CoursePersonMapping;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePersonMappingRepository extends ReactiveCrudRepository<CoursePersonMapping, Long> {

}
