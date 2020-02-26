package edu.gatech.matcha.courseshop.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "votes",
indexes = @Index(columnList = "user_id,review_id", unique = true))
public class Vote {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Account user;

    @ManyToOne
    private Review review;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @Setter
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class VoteId implements Serializable {
        private Account user;
        private Review review;
    }

    @Getter
    public enum Status {

        UPVOTED(1),
        NOT_VOTED(0),
        DOWNVOTED(-1);

        private int value;

        Status(int value) {
            this.value = value;
        }
    }
}
