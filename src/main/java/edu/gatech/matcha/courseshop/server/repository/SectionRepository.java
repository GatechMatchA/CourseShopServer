package edu.gatech.matcha.courseshop.server.repository;

import edu.gatech.matcha.courseshop.server.model.Course;
import edu.gatech.matcha.courseshop.server.model.Professor;
import edu.gatech.matcha.courseshop.server.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findAllByCourseAndProfessor(Course course, Professor professor);
}
