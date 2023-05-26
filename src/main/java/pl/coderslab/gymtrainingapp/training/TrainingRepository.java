package pl.coderslab.gymtrainingapp.training;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.gymtrainingapp.exercise.Exercise;

import java.util.Optional;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    Optional<Training> findByName(String name);
}
