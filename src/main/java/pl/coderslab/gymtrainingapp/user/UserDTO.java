package pl.coderslab.gymtrainingapp.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.gymtrainingapp.training.Training;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    @Size(min = 2, max = 30, message = "too short or to long user name")
    private String name;
    private List<Training> trainings = new ArrayList<>();
}
