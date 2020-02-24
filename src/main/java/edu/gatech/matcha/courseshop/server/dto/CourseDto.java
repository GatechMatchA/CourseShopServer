package edu.gatech.matcha.courseshop.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import edu.gatech.matcha.courseshop.server.model.Course;
import edu.gatech.matcha.courseshop.server.model.CourseProfessor;
import edu.gatech.matcha.courseshop.server.model.Section;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDto {
    private long id;
    private String code;
    private String major;
    private String title;
    private String description;
    private int creditHour;
    private String restrictions;
    private List<Long> professors;
    private List<Long> sections;
    private List<Long> prerequisites;
    private List<Long> dependents;

    public static CourseDto serialize(Course course) {
        return new CourseDto().setId(course.getId())
                              .setCode(course.getCode())
                              .setMajor(course.getMajor())
                              .setTitle(course.getTitle())
                              .setDescription(course.getDescription())
                              .setCreditHour(course.getCreditHour())
                              .setRestrictions(course.getRestrictions())
                              .setProfessors(course.getProfessors()
                                                   .stream()
                                                   .map(CourseProfessor::getId)
                                                   .collect(Collectors.toList()))
                              .setSections(course.getSections()
                                                 .stream()
                                                 .map(Section::getId)
                                                 .collect(Collectors.toList()))
                              .setPrerequisites(course.getPrerequisites()
                                                      .stream()
                                                      .map(Course::getId)
                                                      .collect(Collectors.toList()))
                              .setDependents(course.getDependents()
                                                   .stream()
                                                   .map(Course::getId)
                                                   .collect(Collectors.toList()));
    }
}