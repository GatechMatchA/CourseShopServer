package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.SectionDto;
import edu.gatech.matcha.courseshop.server.model.Course;
import edu.gatech.matcha.courseshop.server.model.Professor;
import edu.gatech.matcha.courseshop.server.repository.CourseRepository;
import edu.gatech.matcha.courseshop.server.repository.ProfessorRepository;
import edu.gatech.matcha.courseshop.server.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService {


    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;


    public SectionServiceImpl(SectionRepository sectionRepository, CourseRepository courseRepository,
                              ProfessorRepository professorRepository) {
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public List<SectionDto> getSectionsForCourseProfessor(long courseId, long professorId) {
        Course course = courseRepository.findById(courseId)
                                        .orElse(null);
        if (course == null) {
            return null;
        }
        Professor professor = professorRepository.findById(professorId)
                                                 .orElse(null);
        if (professor == null) {
            return null;
        }
        return sectionRepository.findAllByCourseAndProfessor(course, professor)
                                .stream()
                                .map(SectionDto::serialize)
                                .collect(Collectors.toList());
    }
}
