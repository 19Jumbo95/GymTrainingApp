package pl.coderslab.gymtrainingapp.exercise;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseDTO {
    private Long id;
    @NotNull
    @Size(min = 2, max = 30, message = "too short or to long exercise name")
    private String name;
    private String bodyPart;
    @Range(min = 1, max = 50, message = "number of reps equal to 0 or greater than 50 ")
    private int reps;
    @Range(min = 1, max = 20, message = "number of sets equal to 0 or greater than 20 ")
    private int sets;
}
