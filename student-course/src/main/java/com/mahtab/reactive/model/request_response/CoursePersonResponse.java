package com.mahtab.reactive.model.request_response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoursePersonResponse {
    private String courseTitle;
    private String firstName;
    private String lastName;
}
