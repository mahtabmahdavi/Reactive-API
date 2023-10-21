package com.mahtab.reactive.service;

import com.mahtab.reactive.exception.CustomException;
import com.mahtab.reactive.model.request_response.CoursePersonRequest;
import com.mahtab.reactive.model.entity.Course;
import com.mahtab.reactive.model.dto.CourseDto;
import com.mahtab.reactive.model.entity.CoursePersonMapping;
import com.mahtab.reactive.model.entity.Person;
import com.mahtab.reactive.model.request_response.CoursePersonResponse;
import com.mahtab.reactive.repository.CoursePersonMappingRepository;
import com.mahtab.reactive.repository.CourseRepository;
import com.mahtab.reactive.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final PersonRepository personRepository;
    private final CoursePersonMappingRepository mappingRepository;
    private final PersonService personService;

    public Mono<Object> create(Course newCourse) {
        return courseRepository.findByTitleIgnoreCase(
                        newCourse.getTitle())
                .flatMap(existingStudent -> Mono.error(new CustomException("This Course is already existed")))
                .switchIfEmpty(courseRepository.save(newCourse));
    }

    public Mono<Object> getCourseByPerson(CoursePersonRequest request) {
        return personRepository.findById(request.getPersonId())
                .switchIfEmpty(Mono.error(new CustomException("This Person doesn't exist")))
                .flatMap(desiredPerson ->
                        courseRepository.findById(request.getCourseId())
                                .switchIfEmpty(Mono.error(new CustomException("This Course doesn't exist")))
                                .flatMap(desiredCourse ->
                                        mappingRepository.existsByCourseAndPerson(request.getCourseId(), request.getPersonId())
                                                .flatMap(mappingExists -> {
                                                    if (mappingExists) {
                                                        return Mono.error(new CustomException("You already choose this course"));
                                                    } else {
                                                        return mappingRepository.save(CoursePersonMapping.builder()
                                                                        .course(request.getCourseId())
                                                                        .person(request.getPersonId())
                                                                        .build())
                                                                .flatMap(savedCoursePerson ->
                                                                        convertCoursePersonMappingToCoursePersonDto(
                                                                                Mono.just(desiredCourse), Mono.just(desiredPerson)
                                                                        )
                                                                );
                                                    }
                                                })
                                )
                );
    }

    public Flux<Course> readAll() {
        return courseRepository.findAll()
                .delayElements(Duration.ofSeconds(1));
    }

//    public Mono<Object> readById(Long id) {
//        return courseRepository.findById(id)
//                .switchIfEmpty(Mono.error(new CustomException("This Course was Not found")))
//                .flatMap(course -> personRepository.findPeopleByCourseId(id)
//                        .collectList()
//                        .map(people -> new CourseDto(course.getTitle(), personService.convertPersonToPersonDto(people))));
//    }

    public Mono<CoursePersonResponse> convertCoursePersonMappingToCoursePersonDto(
            Mono<Course> desiredCourse, Mono<Person> desiredPerson) {
        return Mono.zip(desiredCourse, desiredPerson)
                .map(tuple -> CoursePersonResponse.builder()
                        .courseTitle(tuple.getT1().getTitle())
                        .firstName(tuple.getT2().getFirstName())
                        .lastName(tuple.getT2().getLastName())
                        .build());
    }
}
