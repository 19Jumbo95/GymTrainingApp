package pl.coderslab.gymtrainingapp.user;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users_params")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserParams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private double weight;

    @Column(name = "created_on")
    private LocalDate created0n;

    @PrePersist
    public void prePersist() {
        created0n = LocalDate.now();
    }
}
