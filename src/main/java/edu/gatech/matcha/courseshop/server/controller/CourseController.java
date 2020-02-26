package edu.gatech.matcha.courseshop.server.controller;

import edu.gatech.matcha.courseshop.server.dto.CourseDto;
import edu.gatech.matcha.courseshop.server.dto.CourseProfessorDto;
import edu.gatech.matcha.courseshop.server.response.Response;
import edu.gatech.matcha.courseshop.server.service.CourseProfessorService;
import edu.gatech.matcha.courseshop.server.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseProfessorService courseProfessorService;

    public CourseController(CourseService courseService,
                            CourseProfessorService courseProfessorService) {
        this.courseService = courseService;
        this.courseProfessorService = courseProfessorService;
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
    public ResponseEntity getCourses(@RequestParam(name = "major", required = false) String major,
                                     @RequestParam(name = "orderBy", required = false) String field) {
        List<CourseDto> list;
        if (major != null) {
            list = courseService.getCoursesByMajor(major);
        } else {
            list = courseService.getAllCourses();
        }
        if (field != null) {
            if (field.equals("code")) {
                list.sort(Comparator.comparing(CourseDto::getCode));
            }
        }
        return Response.create(HttpStatus.OK, list);
    }

    @RequestMapping(value = "/{courseId}/professors", method = RequestMethod.GET)
    public ResponseEntity getCourseProfessors(@PathVariable long courseId) {
        List<CourseProfessorDto> list = courseProfessorService.getCourseProfessors(courseId);
        if (list == null) {
            return Response.create(HttpStatus.NO_CONTENT);
        }
        return Response.create(HttpStatus.OK, list);
    }

    @RequestMapping(value = "/{courseId}/professors/{professorId}", method = RequestMethod.GET)
    public ResponseEntity getCourseProfessor(@PathVariable long courseId,
                                             @PathVariable long professorId) {
        CourseProfessorDto courseProfessorDto = courseProfessorService.getCourseProfessor(courseId, professorId);
        if (courseProfessorDto == null) {
            return Response.create(HttpStatus.NO_CONTENT);
        }
        return Response.create(HttpStatus.OK, courseProfessorDto);
    }

}
