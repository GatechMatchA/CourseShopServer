package edu.gatech.matcha.courseshop.server.controller;

import edu.gatech.matcha.courseshop.server.dto.ReviewDto;
import edu.gatech.matcha.courseshop.server.request.AccountRequest;
import edu.gatech.matcha.courseshop.server.request.ReviewRequest;
import edu.gatech.matcha.courseshop.server.response.Response;
import edu.gatech.matcha.courseshop.server.service.ReviewService;
import edu.gatech.matcha.courseshop.server.util.BasicAuth;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {


    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody @Valid ReviewRequest reviewRequest,
                                 @RequestHeader(name = "Authorization", required = true) String authHeader) {
        AccountRequest accountRequest = BasicAuth.decode(authHeader);
        if (!BasicAuth.authenticated(accountRequest)) {
            return Response.create(HttpStatus.UNAUTHORIZED);
        }
        ReviewDto reviewDto = reviewService.createReview(reviewRequest, accountRequest);
        if (reviewDto == null) {
            return Response.create(HttpStatus.BAD_REQUEST, "Invalid arguments: some of courseId, professorId, author must be wrong.");
        }
        return Response.create(HttpStatus.OK, reviewDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getReviews(@RequestParam(name = "course") long courseId,
                                     @RequestParam(name = "professor") long professorId) {
        List<ReviewDto> reviews = reviewService.getReviews(courseId, professorId);
        if (reviews == null) {
            return Response.create(HttpStatus.BAD_REQUEST, "Invalid argument(s): course and/or professor does not exist.");
        }
        return Response.create(HttpStatus.OK, reviews);
    }
}
