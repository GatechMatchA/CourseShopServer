package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.CourseDto;
import edu.gatech.matcha.courseshop.server.model.Course;
import edu.gatech.matcha.courseshop.server.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseProfessorService courseProfessorService;

    public CourseServiceImpl(CourseRepository courseRepository, CourseProfessorService courseProfessorService) {
        this.courseRepository = courseRepository;
        this.courseProfessorService = courseProfessorService;
    }

    @Override
    public CourseDto getCourseById(long id) {
        Course course = courseRepository.findById(id)
                                        .orElse(null);
        if (course == null) return null;
        return CourseDto.serialize(course);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll()
                               .stream()
                               .map(CourseDto::serialize)
                               .collect(Collectors.toList());
    }

    @Override
    public List<CourseDto> getCoursesByMajor(String major) {
        return courseRepository.findCoursesByMajor(major)
                               .stream()
                               .map(CourseDto::serialize)
                               .collect(Collectors.toList());
    }
}
