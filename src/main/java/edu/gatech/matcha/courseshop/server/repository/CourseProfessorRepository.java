package edu.gatech.matcha.courseshop.server.repository;

import edu.gatech.matcha.courseshop.server.model.Course;
import edu.gatech.matcha.courseshop.server.model.CourseProfessor;
import edu.gatech.matcha.courseshop.server.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseProfessorRepository extends JpaRepository<CourseProfessor, Long> {

    Optional<CourseProfessor> findByCourseAndProfessor(Course course, Professor professor);
}
