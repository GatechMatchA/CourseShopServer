package edu.gatech.matcha.courseshop.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class SectionDto {
    private long id;
    private long course;
    private String sectionCode;
    private long professor;
    private String CRN;
    private List<ClassTimeDto> meetingTimes;

    public static SectionDto serialize(Section section) {
        return new SectionDto().setId(section.getId())
                               .setCourse(section.getCourse()
                                                 .getId())
                               .setSectionCode(section.getSectionCode())
                               .setProfessor(section.getProfessor()
                                                    .getId())
                               .setCRN(section.getCRN())
                               .setMeetingTimes(section.getMeetingTimes()
                                                       .stream()
                                                       .map(ClassTimeDto::serialize)
                                                       .collect(Collectors.toList()));
    }
}
