package pl.coderslab.gymtrainingapp.exercise;

import lombok.*;

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
    private int reps;
    private int sets;
}
