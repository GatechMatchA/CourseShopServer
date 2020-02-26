package edu.gatech.matcha.courseshop.server.repository;

import edu.gatech.matcha.courseshop.server.model.Account;
import edu.gatech.matcha.courseshop.server.model.Review;
import edu.gatech.matcha.courseshop.server.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByUserAndReview(Account user, Review review);
}
