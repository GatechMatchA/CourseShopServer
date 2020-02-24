package edu.gatech.matcha.courseshop.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@IdClass(CourseProfessor.class)
@Table(name = "gpa")
public class Gpa {

    @Id
    @ManyToOne
    private Course course;

    @Id
    @ManyToOne
    private Professor professor;

    private float averageGpa;

    private float APercentage;

    private float BPercentage;

    private float CPercentage;

    private float DPercentage;

    private float FPercentage;

    private float WPercentage;
}
