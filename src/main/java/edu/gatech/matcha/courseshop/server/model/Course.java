package edu.gatech.matcha.courseshop.server.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "courses", indexes = @Index(columnList = "code"))
public class Course {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String code;

    private String major;

    private String title;

    private String description;

    private int creditHour;

    private String restrictions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private Set<CourseProfessor> professors;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private Set<Section> sections;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "prerequisites")
    private Set<Course> prerequisites;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "prerequisites")
    private Set<Course> dependents;
}
