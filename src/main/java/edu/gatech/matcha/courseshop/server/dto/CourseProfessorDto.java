package edu.gatech.matcha.courseshop.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import edu.gatech.matcha.courseshop.server.model.CourseProfessor;
import edu.gatech.matcha.courseshop.server.model.GradeDistribution;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseProfessorDto {
    private long course;
    private long professor;
    private float averageGpa;
    private GradeDistribution gradeDistribution;
    private List<Long> reviews;

    // not part of model class
    private float averageQuality;
    private float averageEasiness;

    public static CourseProfessorDto serialize(CourseProfessor courseProfessor) {
        return new CourseProfessorDto().setCourse(courseProfessor.getCourse()
                                                                 .getId())
                                       .setProfessor(courseProfessor.getProfessor()
                                                                    .getId())
                                       .setAverageGpa(courseProfessor.getAverageGpa())
                                       .setGradeDistribution(courseProfessor.getGradeDistribution());
    }
}
