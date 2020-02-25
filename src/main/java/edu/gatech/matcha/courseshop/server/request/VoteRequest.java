package edu.gatech.matcha.courseshop.server.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VoteRequest {

    @NotNull
    private Long review;

    @Range(min = -1, max = 1, message = "The status should be -1 for downvote, 0 for canceling any votes, 1 for upvote.")
    private int status;
}
