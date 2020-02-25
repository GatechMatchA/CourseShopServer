package edu.gatech.matcha.courseshop.server.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewRequest {

    @NotNull
    private Long professor;

    @NotNull
    private Long course;

    @NotNull
    private Float quality;

    private Float easiness;

    private String comment;
}
