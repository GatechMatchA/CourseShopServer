package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.CourseProfessorDto;
import edu.gatech.matcha.courseshop.server.model.CourseProfessor;

import java.util.List;

public interface CourseProfessorService {

//    float calculateAverageQuality(CourseProfessor courseProfessor);
//
//    float calculateAverageEasiness(CourseProfessor courseProfessor);

    List<CourseProfessorDto> getCourseProfessors(long courseId);

    CourseProfessorDto getCourseProfessor(long courseId, long professorId);
}
