package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.VoteDto;
import edu.gatech.matcha.courseshop.server.request.AccountRequest;
import edu.gatech.matcha.courseshop.server.request.VoteRequest;

public interface VoteService {

    VoteDto updateVote(VoteRequest voteRequest, AccountRequest accountRequest);
}
