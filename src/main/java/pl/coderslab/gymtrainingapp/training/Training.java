package pl.coderslab.gymtrainingapp.training;

import lombok.*;
import pl.coderslab.gymtrainingapp.exercise.Exercise;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trainings")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Exercise> exerciseList = new ArrayList<>();

    private LocalDateTime executionDate;

}
