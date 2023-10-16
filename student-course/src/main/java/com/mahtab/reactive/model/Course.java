package com.mahtab.reactive.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "courses")
public class Course {
    @Id
    @Column(value = "id")
    private Long id;

    @Column(value = "title")
    private String title;

    @Column(value = "teacher")
    private String teacher;
}
