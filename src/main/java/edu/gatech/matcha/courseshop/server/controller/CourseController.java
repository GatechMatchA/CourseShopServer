package edu.gatech.matcha.courseshop.server.controller;

import edu.gatech.matcha.courseshop.server.dto.CourseDto;
import edu.gatech.matcha.courseshop.server.response.Response;
import edu.gatech.matcha.courseshop.server.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getCourse(@PathVariable long id) {
        CourseDto courseDto = courseService.getCourseById(id);
        if (courseDto == null) {
            return Response.create(HttpStatus.NO_CONTENT);
        }
        return Response.create(HttpStatus.OK, courseDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getCourses(@RequestParam(name = "major", required = false) String major) {
        if (major == null) {
            return Response.create(HttpStatus.OK, courseService.getAllCourses());
        }
        return Response.create(HttpStatus.OK, courseService.getCoursesByMajor(major));
    }

}
