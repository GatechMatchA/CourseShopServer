package edu.gatech.matcha.courseshop.server.repository;

import edu.gatech.matcha.courseshop.server.model.CourseProfessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseProfessorRepository extends JpaRepository<CourseProfessor, Long> {

}
