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
@Table(name = "people")
public class Person {
    @Id
    @Column(value = "id")
    private Long id;

    @Column(value = "first_name")
    private String firstName;

    @Column(value = "last_name")
    private String lastName;

    @Column(value = "age")
    private Integer age;
}
