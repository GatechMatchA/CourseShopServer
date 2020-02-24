package edu.gatech.matcha.courseshop.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "course_professor", uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "professor_id"}))
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
