package com.mahtab.reactive.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "course_person_mapping")
public class CoursePersonMapping {
        @Id
        @Column(value = "id")
        private Long id;

        @Column(value = "course")
        private final Long course;

        @Column(value = "person")
        private final Long person;
}
