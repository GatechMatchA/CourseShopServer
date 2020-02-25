package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.ReviewDto;
import edu.gatech.matcha.courseshop.server.model.Account;
import edu.gatech.matcha.courseshop.server.model.Course;
import edu.gatech.matcha.courseshop.server.model.Professor;
import edu.gatech.matcha.courseshop.server.model.Review;
import edu.gatech.matcha.courseshop.server.repository.AccountRepository;
import edu.gatech.matcha.courseshop.server.repository.CourseRepository;
import edu.gatech.matcha.courseshop.server.repository.ProfessorRepository;
import edu.gatech.matcha.courseshop.server.repository.ReviewRepository;
import edu.gatech.matcha.courseshop.server.request.AccountRequest;
import edu.gatech.matcha.courseshop.server.request.ReviewRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final AccountRepository accountRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CourseRepository courseRepository,
                             ProfessorRepository professorRepository, AccountRepository accountRepository) {
        this.reviewRepository = reviewRepository;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public ReviewDto createReview(ReviewRequest reviewRequest, AccountRequest accountRequest) {
        Course course = courseRepository.findById(reviewRequest.getCourse())
                                        .orElse(null);
        if (course == null) {
            return null;
        }

        Professor professor = professorRepository.findById(reviewRequest.getProfessor())
                                                 .orElse(null);
        if (professor == null) {
            return null;
        }

        Account account = accountRepository.findByUsername(accountRequest.getUsername())
                                           .orElse(null);
        if (account == null) {
            return null;
        }

        if (reviewRepository.existsByAuthorAndCourseAndProfessor(account, course, professor)) {
            return null;
        }

        Review review = new Review().setAuthor(account)
                                    .setCourse(course)
                                    .setProfessor(professor)
                                    .setQuality(reviewRequest.getQuality())
                                    .setEasiness(reviewRequest.getEasiness())
                                    .setComment(reviewRequest.getComment())
                                    .setUpvote(0)
                                    .setDownvote(0)
                                    .setTimestamp(new Date());
        reviewRepository.saveAndFlush(review);
        return ReviewDto.serialize(review);
    }

    @Override
    public List<ReviewDto> getReviews(long courseId, long professorId) {
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

        return reviewRepository.findAllByCourseAndProfessor(course, professor)
                               .stream()
                               .map(ReviewDto::serialize)
                               .collect(Collectors.toList());
    }
}
