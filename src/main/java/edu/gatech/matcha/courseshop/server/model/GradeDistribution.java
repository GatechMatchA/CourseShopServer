package edu.gatech.matcha.courseshop.server.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class GradeDistribution {
    private float A;
    private float B;
    private float C;
    private float D;
    private float F;
    private float W;
}
