package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.CourseProfessorDto;
import edu.gatech.matcha.courseshop.server.model.Course;
import edu.gatech.matcha.courseshop.server.model.CourseProfessor;
import edu.gatech.matcha.courseshop.server.model.Professor;
import edu.gatech.matcha.courseshop.server.model.Review;
import edu.gatech.matcha.courseshop.server.repository.CourseProfessorRepository;
import edu.gatech.matcha.courseshop.server.repository.CourseRepository;
import edu.gatech.matcha.courseshop.server.repository.ProfessorRepository;
import edu.gatech.matcha.courseshop.server.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CourseProfessorServiceImpl implements CourseProfessorService {

    private final ReviewRepository reviewRepository;
    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final CourseProfessorRepository courseProfessorRepository;

    public CourseProfessorServiceImpl(ReviewRepository reviewRepository,
                                      CourseRepository courseRepository,
                                      ProfessorRepository professorRepository,
                                      CourseProfessorRepository courseProfessorRepository) {
        this.reviewRepository = reviewRepository;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.courseProfessorRepository = courseProfessorRepository;
    }

    float calculateAverageQuality(CourseProfessor courseProfessor) {
        return (float) reviewRepository.findAllByCourseAndProfessor(courseProfessor.getCourse(), courseProfessor.getProfessor())
                                       .stream()
                                       .filter(review -> review.getQuality() >= 0 && review.getQuality() <= 1)
                                       .mapToDouble(Review::getQuality)
                                       .average()
                                       .orElse(-1);
    }

    float calculateAverageEasiness(CourseProfessor courseProfessor) {
        return (float) reviewRepository.findAllByCourseAndProfessor(courseProfessor.getCourse(), courseProfessor.getProfessor())
                                       .stream()
                                       .filter(review -> review.getEasiness() >= 0 && review.getEasiness() <= 1)
                                       .mapToDouble(Review::getEasiness)
                                       .average()
                                       .orElse(-1);
    }

    @Override
    public List<CourseProfessorDto> getCourseProfessors(long courseId) {
        Course course = courseRepository.findById(courseId)
                                        .orElse(null);
        if (course == null) return null;
        Set<CourseProfessor> courseProfessors = course.getProfessors();
        List<CourseProfessorDto> courseProfessorDtos = new ArrayList<>();
        for (CourseProfessor courseProfessor : courseProfessors) {
            courseProfessorDtos.add(CourseProfessorDto.serialize(courseProfessor)
                                                      .setAverageQuality(calculateAverageQuality(courseProfessor))
                                                      .setAverageEasiness(calculateAverageEasiness(courseProfessor)));
        }
        return courseProfessorDtos;
    }

    @Override
    public CourseProfessorDto getCourseProfessor(long courseId, long professorId) {
        Course course = courseRepository.findById(courseId)
                                        .orElse(null);
        if (course == null) return null;
        Professor professor = professorRepository.findById(professorId)
                                                 .orElse(null);
        if (professor == null) return null;
        CourseProfessor courseProfessor = courseProfessorRepository.findByCourseAndProfessor(course, professor)
                                                                   .orElse(null);
        if (courseProfessor == null) return null;
        return CourseProfessorDto.serialize(courseProfessor)
                                 .setAverageQuality(calculateAverageQuality(courseProfessor))
                                 .setAverageEasiness(calculateAverageEasiness(courseProfessor));
    }
}
