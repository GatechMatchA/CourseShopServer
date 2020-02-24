package edu.gatech.matcha.courseshop.server.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "courses", uniqueConstraints = @UniqueConstraint(columnNames = "courseCode"), indexes = @Index(columnList = "courseCode"))
public class Course {

    @Id
    private long id;

    private String major;

    private String courseCode;

    private String title;

    private String description;

    private int creditHour;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private Set<Section> sections;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "prerequisites")
    private Set<Course> prerequisites;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "prerequisites")
    private Set<Course> dependents;

    private String restrictions;
}
