package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.ReviewDto;
import edu.gatech.matcha.courseshop.server.model.Account;
import edu.gatech.matcha.courseshop.server.model.CourseProfessor;
import edu.gatech.matcha.courseshop.server.model.Review;
import edu.gatech.matcha.courseshop.server.repository.AccountRepository;
import edu.gatech.matcha.courseshop.server.repository.CourseProfessorRepository;
import edu.gatech.matcha.courseshop.server.repository.ReviewRepository;
import edu.gatech.matcha.courseshop.server.request.AccountRequest;
import edu.gatech.matcha.courseshop.server.request.ReviewRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final CourseProfessorRepository courseProfessorRepository;
    private final AccountRepository accountRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CourseProfessorRepository courseProfessorRepository,
                             @Qualifier("accountRepository") AccountRepository accountRepository) {
        this.reviewRepository = reviewRepository;
        this.courseProfessorRepository = courseProfessorRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public ReviewDto createReview(ReviewRequest reviewRequest, AccountRequest accountRequest) {
        CourseProfessor courseProfessor = courseProfessorRepository.findCourseProfessorByCourseIdAndProfessorId(
        reviewRequest.getCourse(), reviewRequest.getProfessor())
                                                                   .orElse(null);
        if (courseProfessor == null) {
            return null;
        }
        Account account = accountRepository.findByUsername(accountRequest.getUsername())
                                           .orElse(null);
        if (account == null) {
            return null;
        }
        if (reviewRepository.existsByAuthorAndCourseProfessor(account, courseProfessor)) {
            return null;
        }
        Review review = new Review().setAuthor(account)
                                    .setCourseProfessor(courseProfessor)
                                    .setQuality(reviewRequest.getQuality())
                                    .setEasiness(reviewRequest.getEasiness())
                                    .setComment(reviewRequest.getComment())
                                    .setUpvote(0)
                                    .setDownvote(0)
                                    .setTimestamp(new Date());
        reviewRepository.saveAndFlush(review);
        return ReviewDto.serialize(review);
    }
}
