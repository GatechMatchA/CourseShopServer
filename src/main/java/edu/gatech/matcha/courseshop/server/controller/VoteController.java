package edu.gatech.matcha.courseshop.server.controller;

import edu.gatech.matcha.courseshop.server.dto.VoteDto;
import edu.gatech.matcha.courseshop.server.request.AccountRequest;
import edu.gatech.matcha.courseshop.server.request.VoteRequest;
import edu.gatech.matcha.courseshop.server.response.Response;
import edu.gatech.matcha.courseshop.server.service.VoteService;
import edu.gatech.matcha.courseshop.server.util.BasicAuth;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/votes")
public class VoteController {


    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity voteReview(@RequestBody @Valid VoteRequest voteRequest,
                                     @RequestHeader(value = "Authorization", required = true) String authHeader) {
        AccountRequest accountRequest = BasicAuth.decode(authHeader);
        if (!BasicAuth.authenticated(accountRequest)) {
            return Response.create(HttpStatus.UNAUTHORIZED);
        }

        VoteDto voteDto = voteService.updateVote(voteRequest, accountRequest);
        if (voteDto == null) {
            return Response.create(HttpStatus.BAD_REQUEST, "Invalid request: either reviewId or status is invalid.");
        }
        return Response.create(HttpStatus.OK, voteDto);
    }
}
