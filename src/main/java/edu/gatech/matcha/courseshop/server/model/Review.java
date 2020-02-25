package edu.gatech.matcha.courseshop.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "reviews",
uniqueConstraints = @UniqueConstraint(columnNames = {"author_id", "course_id", "professor_id"}),
indexes = @Index(columnList = "author_id,course_id,professor_id", unique = true))
public class Review {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account author;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    private Professor professor;

    private Date timestamp;

    private float quality;

    private float easiness;

    @Transient
    private int upvote;

    @Transient
    private int downvote;

    private String comment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review")
    private Set<Vote> votes;

    @PostLoad
    void countVotes() {
        upvote = (int) votes.stream()
                            .filter(vote -> vote.getStatus()
                                                .equals(Vote.Status.UPVOTED))
                            .count();
        downvote = (int) votes.stream()
                              .filter(vote -> vote.getStatus()
                                                  .equals(Vote.Status.DOWNVOTED))
                              .count();
    }
}
