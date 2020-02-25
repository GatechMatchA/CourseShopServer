package edu.gatech.matcha.courseshop.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "professors")
public class Professor {

    @Id
    @GeneratedValue
    private long id;

    private String lastName;

    private String firstName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professor")
    private Set<CourseProfessor> courses;
}
