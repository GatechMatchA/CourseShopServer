package edu.gatech.matcha.courseshop.server.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
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
@EqualsAndHashCode
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "sections", uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "sectionCode"}))
public class Section {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @Column(nullable = false)
    private String sectionCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Professor professor;

    private String CRN;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section")
    private Set<ClassTime> meetingTimes;
}
