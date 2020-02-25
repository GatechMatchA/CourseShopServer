package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.ReviewDto;
import edu.gatech.matcha.courseshop.server.request.AccountRequest;
import edu.gatech.matcha.courseshop.server.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(ReviewRequest reviewRequest, AccountRequest accountRequest);

    List<ReviewDto> getReviews(long courseId, long professorId);
}
