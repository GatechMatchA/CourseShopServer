package edu.gatech.matcha.courseshop.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "course_professor",
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
}
