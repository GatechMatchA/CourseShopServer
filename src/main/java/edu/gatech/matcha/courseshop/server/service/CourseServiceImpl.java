package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.CourseDto;
import edu.gatech.matcha.courseshop.server.dto.CourseProfessorDto;
import edu.gatech.matcha.courseshop.server.model.Course;
import edu.gatech.matcha.courseshop.server.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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

    @Override
    public List<CourseProfessorDto> getCourseProfessors(long courseId) {
        Course course = courseRepository.findById(courseId)
                                        .orElse(null);
        if (course == null) return null;
        return course.getProfessors()
                     .stream()
                     .map(CourseProfessorDto::serialize)
                     .collect(Collectors.toList());
    }
}
