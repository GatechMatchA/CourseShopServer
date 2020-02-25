package edu.gatech.matcha.courseshop.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import edu.gatech.matcha.courseshop.server.model.Vote;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VoteDto {
    private long user;
    private long review;
    private int status;

    public static VoteDto serialize(Vote vote) {
        return new VoteDto().setUser(vote.getUser()
                                         .getId())
                            .setReview(vote.getReview()
                                           .getId())
                            .setStatus(vote.getStatus()
                                           .getValue());
    }
}
