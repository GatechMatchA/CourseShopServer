package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.CourseDto;

import java.util.List;

public interface CourseService {

    CourseDto getCourseById(long id);

    List<CourseDto> getAllCourses();

    List<CourseDto> getCoursesByMajor(String major);
}
