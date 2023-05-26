package pl.coderslab.gymtrainingapp.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.gymtrainingapp.exercise.Exercise;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingDTO {
    private Long id;
    @NotNull
    @Size(min = 2, max = 30, message = "too short or to long training name")
    private String name;
    private List<Exercise> exerciseList;
    private LocalDateTime executionDate;

}
