package com.mahtab.reactive.model;


import lombok.*;
import reactor.core.publisher.Flux;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {

    private String title;
    private List<Person> participants;
}
