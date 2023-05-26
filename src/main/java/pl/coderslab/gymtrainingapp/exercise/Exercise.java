package pl.coderslab.gymtrainingapp.exercise;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "exercises")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "body_part", nullable = false)
    private String bodyPart;

    private int reps;

    private int sets;

}
