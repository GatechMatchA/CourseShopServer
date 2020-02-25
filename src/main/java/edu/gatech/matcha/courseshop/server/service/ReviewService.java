package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.ReviewDto;
import edu.gatech.matcha.courseshop.server.request.AccountRequest;
import edu.gatech.matcha.courseshop.server.request.ReviewRequest;

public interface ReviewService {

    ReviewDto createReview(ReviewRequest reviewRequest, AccountRequest accountRequest);
}
