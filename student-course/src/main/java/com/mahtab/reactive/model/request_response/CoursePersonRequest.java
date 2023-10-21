package com.mahtab.reactive.model.request_response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoursePersonRequest {
    private Long courseId;
    private Long personId;
}
