package edu.gatech.matcha.courseshop.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "reviews",
uniqueConstraints = @UniqueConstraint(columnNames = {"course_professor_id", "author_id"}))
public class Review {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private CourseProfessor courseProfessor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account author;

    private Date timestamp;

    private float quality;

    private float easiness;

    private int upvote;

    private int downvote;

    private String comment;
}
