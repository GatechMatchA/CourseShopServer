package edu.gatech.matcha.courseshop.server.repository;

import edu.gatech.matcha.courseshop.server.model.Account;
import edu.gatech.matcha.courseshop.server.model.Review;
import edu.gatech.matcha.courseshop.server.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Vote.VoteId> {

    Optional<Vote> findByUserAndReview(Account user, Review review);
}
