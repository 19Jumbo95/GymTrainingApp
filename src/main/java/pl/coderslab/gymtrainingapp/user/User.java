package pl.coderslab.gymtrainingapp.user;

import lombok.*;
import pl.coderslab.gymtrainingapp.training.Training;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    private List<Training> trainings = new ArrayList<>();

}
