package edu.gatech.matcha.courseshop.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@IdClass(Section.CourseSection.class)
@Table(name = "sections")
public class Section {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @Id
    private String sectionCode;

    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public class CourseSection implements Serializable {

        private Course course;

        private String sectionCode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Professor professor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section")
    private Set<ClassTime> times;
}
