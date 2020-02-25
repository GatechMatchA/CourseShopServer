package edu.gatech.matcha.courseshop.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "course_professor",
uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "professor_id"}),
indexes = @Index(columnList = "course_id,professor_id", unique = true))
public class CourseProfessor {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Professor professor;

    private float averageGpa;

    @Embedded
    private GradeDistribution gradeDistribution;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courseProfessor")
    private Set<Review> reviews;
}
