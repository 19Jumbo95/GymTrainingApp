package pl.coderslab.gymtrainingapp.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserParamsDTO {
    private Long id;
    private Long userId;
    private double weight;
    private LocalDate createdOn;
}
