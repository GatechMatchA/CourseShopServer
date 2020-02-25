package edu.gatech.matcha.courseshop.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "course_professor",
uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "professor_id"}),
indexes = @Index(columnList = "course_id,professor_id", unique = true))
@IdClass(CourseProfessor.CourseProfessorId.class)
public class CourseProfessor {

    @Getter
    @Setter
    @NoArgsConstructor
    @Accessors(chain = true)
    static class CourseProfessorId implements Serializable {
        private Course course;
        private Professor professor;
    }

    @Id
    @ManyToOne
    private Course course;

    @Id
    @ManyToOne
    private Professor professor;

    private float averageGpa;

    @Embedded
    private GradeDistribution gradeDistribution;
}
