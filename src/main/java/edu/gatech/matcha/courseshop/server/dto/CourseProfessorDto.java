package edu.gatech.matcha.courseshop.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import edu.gatech.matcha.courseshop.server.model.CourseProfessor;
import edu.gatech.matcha.courseshop.server.model.GradeDistribution;
import edu.gatech.matcha.courseshop.server.model.Review;
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
public class CourseProfessorDto {
    private long id;
    private long course;
    private long professor;
    private float averageGpa;
    private GradeDistribution gradeDistribution;
    private List<Long> reviews;

    public static CourseProfessorDto serialize(CourseProfessor courseProfessor) {
        return new CourseProfessorDto().setId(courseProfessor.getId())
                                       .setCourse(courseProfessor.getCourse()
                                                                 .getId())
                                       .setProfessor(courseProfessor.getProfessor()
                                                                    .getId())
                                       .setAverageGpa(courseProfessor.getAverageGpa())
                                       .setGradeDistribution(courseProfessor.getGradeDistribution())
                                       .setReviews(courseProfessor.getReviews()
                                                                  .stream()
                                                                  .map(Review::getId)
                                                                  .collect(Collectors.toList()));
    }
}
