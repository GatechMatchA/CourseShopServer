package edu.gatech.matcha.courseshop.server.controller;

import edu.gatech.matcha.courseshop.server.dto.ProfessorDto;
import edu.gatech.matcha.courseshop.server.response.Response;
import edu.gatech.matcha.courseshop.server.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/professors")
public class ProfessorController {


    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getProfessor(@PathVariable long id) {
        ProfessorDto professorDto = professorService.getProfessor(id);
        if (professorDto == null) {
            return Response.create(HttpStatus.NO_CONTENT, "There is no professor with the id <" + id + ">.");
        }
        return Response.create(HttpStatus.OK, professorDto);
    }
}
