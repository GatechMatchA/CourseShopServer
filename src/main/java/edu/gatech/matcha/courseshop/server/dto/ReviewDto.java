package edu.gatech.matcha.courseshop.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import edu.gatech.matcha.courseshop.server.model.Review;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewDto {
    private long id;
    private long course;
    private long professor;
    private long author;
    private Date timestamp;
    private float quality;
    private float easiness;
    private int upvote;
    private int downvote;
    private String comment;

    public static ReviewDto serialize(Review review) {
        return new ReviewDto().setId(review.getId())
                              .setCourse(review.getCourse()
                                               .getId())
                              .setProfessor(review.getProfessor()
                                                  .getId())
                              .setAuthor(review.getAuthor()
                                               .getId())
                              .setTimestamp(review.getTimestamp())
                              .setQuality(review.getQuality())
                              .setEasiness(review.getEasiness())
                              .setUpvote(review.getUpvote())
                              .setDownvote(review.getDownvote())
                              .setComment(review.getComment());
    }
}
