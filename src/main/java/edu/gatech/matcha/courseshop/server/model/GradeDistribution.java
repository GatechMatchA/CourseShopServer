package edu.gatech.matcha.courseshop.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.ALWAYS)
public class GradeDistribution {
    private float A;
    private float B;
    private float C;
    private float D;
    private float F;
    private float W;
}
