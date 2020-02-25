package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.SectionDto;

import java.util.List;

public interface SectionService {

    List<SectionDto> getSectionsForCourseProfessor(long courseId, long professorId);

}
