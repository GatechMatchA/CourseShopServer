package edu.gatech.matcha.courseshop.server.repository;

import edu.gatech.matcha.courseshop.server.model.Account;
import edu.gatech.matcha.courseshop.server.model.Course;
import edu.gatech.matcha.courseshop.server.model.Professor;
import edu.gatech.matcha.courseshop.server.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByAuthorAndCourseAndProfessor(Account author, Course course, Professor professor);
}
