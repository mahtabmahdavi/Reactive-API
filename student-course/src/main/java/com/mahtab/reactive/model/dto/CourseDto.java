package com.mahtab.reactive.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {

    private String title;
    private List<PersonDto> participants;
}
