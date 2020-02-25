package edu.gatech.matcha.courseshop.server.controller;

import edu.gatech.matcha.courseshop.server.dto.SectionDto;
import edu.gatech.matcha.courseshop.server.response.Response;
import edu.gatech.matcha.courseshop.server.service.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/sections")
public class SectionController {


    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getSections(@RequestParam(name = "course") long courseId,
                                      @RequestParam(name = "professor") long professorId) {
        List<SectionDto> sections = sectionService.getSectionsForCourseProfessor(courseId, professorId);
        if (sections == null) {
            return Response.create(HttpStatus.BAD_REQUEST);
        }
        return Response.create(HttpStatus.OK, sections);
    }
}
