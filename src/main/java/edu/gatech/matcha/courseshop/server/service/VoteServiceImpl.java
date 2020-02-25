package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.VoteDto;
import edu.gatech.matcha.courseshop.server.model.Account;
import edu.gatech.matcha.courseshop.server.model.Review;
import edu.gatech.matcha.courseshop.server.model.Vote;
import edu.gatech.matcha.courseshop.server.repository.AccountRepository;
import edu.gatech.matcha.courseshop.server.repository.ReviewRepository;
import edu.gatech.matcha.courseshop.server.repository.VoteRepository;
import edu.gatech.matcha.courseshop.server.request.AccountRequest;
import edu.gatech.matcha.courseshop.server.request.VoteRequest;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {


    private final VoteRepository voteRepository;
    private final AccountRepository accountRepository;
    private final ReviewRepository reviewRepository;

    public VoteServiceImpl(VoteRepository voteRepository, AccountRepository accountRepository, ReviewRepository reviewRepository) {
        this.voteRepository = voteRepository;
        this.accountRepository = accountRepository;
        this.reviewRepository = reviewRepository;
    }


    @Override
    public VoteDto updateVote(VoteRequest voteRequest, AccountRequest accountRequest) {
        Account user = accountRepository.findByUsername(accountRequest.getUsername())
                                        .orElse(null);
        if (user == null) {
            return null;
        }

        Review review = reviewRepository.findById(voteRequest.getReview())
                                        .orElse(null);
        if (review == null) {
            return null;
        }

        Vote vote = voteRepository.findByUserAndReview(user, review)
                                  .orElse(new Vote().setUser(user)
                                                    .setReview(review));
        switch (voteRequest.getStatus()) {
            case -1:
                voteRepository.save(vote.setStatus(Vote.Status.DOWNVOTED));
                break;
            case 0:
                voteRepository.delete(vote.setStatus(Vote.Status.NOT_VOTED));
                break;
            case 1:
                voteRepository.save(vote.setStatus(Vote.Status.UPVOTED));
                break;
            default:
                return null;
        }
        return VoteDto.serialize(vote);
    }
}
