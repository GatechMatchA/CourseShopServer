package edu.gatech.matcha.courseshop.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import edu.gatech.matcha.courseshop.server.model.Professor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfessorDto {
    private long id;
    private String lastName;
    private String firstName;

    public static ProfessorDto serialize(Professor professor) {
        return new ProfessorDto().setId(professor.getId())
                                 .setLastName(professor.getLastName())
                                 .setFirstName(professor.getFirstName());
    }
}
