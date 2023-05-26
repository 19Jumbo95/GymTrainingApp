package pl.coderslab.gymtrainingapp.exercise;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findAllByBodyPart(String bodyPart);
    Optional<Exercise> findByName(String name);
}
