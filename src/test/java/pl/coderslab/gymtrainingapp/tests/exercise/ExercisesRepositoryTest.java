package pl.coderslab.gymtrainingapp.tests.exercise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.coderslab.gymtrainingapp.exercise.Exercise;
import pl.coderslab.gymtrainingapp.exercise.ExerciseRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ExercisesRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Test
    public void givenExercise_whenSearchForExerciseWithSameName_thenFindExercise() {
        Exercise exercise = Exercise.builder().name("lat pulldown").bodyPart("back").build();
        entityManager.persist(exercise);

        Optional<Exercise> actual = exerciseRepository.findByName("lat pulldown");

        assertThat(actual).isPresent();
        assertThat(actual).hasValue(exercise);
    }

    @Test
    public void givenExercise_whenSearchForExerciseWithDifferentName_thenFindNothing() {
        Exercise exercise = Exercise.builder().name("lat pulldown").bodyPart("back").build();
        entityManager.persist(exercise);

        Optional<Exercise> actual = exerciseRepository.findByName("bench press");

        assertThat(actual).isEmpty();
    }

    @Test
    public void givenExerciseOneAndExerciseTwo_whenSearchForExerciseWithExistingBodyPart_thenFindExercises() {
        Exercise exercise = Exercise.builder().name("lat pulldown").bodyPart("back").build();
        entityManager.persist(exercise);
        Exercise exercise1 = Exercise.builder().name("bent over row").bodyPart("back").build();
        entityManager.persist(exercise1);

        List<Exercise> actual = exerciseRepository.findAllByBodyPart("back");

        assertThat(actual).containsExactly(exercise, exercise1);
    }

    @Test
    public void givenExerciseOneAndExerciseTwo_whenSearchForExerciseWithNoExistingBodyPart_thenFindNothing() {
        Exercise exercise = Exercise.builder().name("lat pulldown").bodyPart("back").build();
        entityManager.persist(exercise);
        Exercise exercise1 = Exercise.builder().name("bent over row").bodyPart("back").build();
        entityManager.persist(exercise1);

        List<Exercise> actual = exerciseRepository.findAllByBodyPart("chest");

        assertThat(actual).isEmpty();
    }
}
