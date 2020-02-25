package edu.gatech.matcha.courseshop.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import edu.gatech.matcha.courseshop.server.model.ClassTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassTimeDto {
    private long id;
    private long section;
    private int dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public static ClassTimeDto serialize(ClassTime classTime) {
        return new ClassTimeDto().setId(classTime.getId())
                                 .setSection(classTime.getSection()
                                                      .getId())
                                 .setDayOfWeek(classTime.getDay()
                                                        .getValue())
                                 .setStartTime(classTime.getStartTime())
                                 .setEndTime(classTime.getEndTime());
    }
}
