package pl.coderslab.gymtrainingapp.tests.training;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.coderslab.gymtrainingapp.exercise.Exercise;
import pl.coderslab.gymtrainingapp.exercise.ExerciseRepository;
import pl.coderslab.gymtrainingapp.training.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainingServiceImplTest {
    private TrainingRepository trainingRepository;
    private ExerciseRepository exerciseRepository;
    private TrainingService trainingService;

    @BeforeEach
    public void setUp() {
        trainingRepository = Mockito.mock(TrainingRepository.class);
        exerciseRepository = Mockito.mock(ExerciseRepository.class);
        trainingService = new TrainingServiceImpl(trainingRepository, exerciseRepository);
    }

    @Test
    public void givenSomeExercises_whenGenerateTraining_thenShouldReturnedGeneratedTraining() {

        Exercise exercise = new Exercise(1L, "Bench press", "chest", 6, 3);
        Exercise exercise1 = new Exercise(2L, "Lat pulldown", "back", 6, 3);
        Exercise exercise2 = new Exercise(3L, "Dumbbell press", "chest", 6, 3);
        TrainingDTO trainingDTO = new TrainingDTO(1L, "Generated training", List.of(exercise, exercise1, exercise2), null);
        Mockito.when(exerciseRepository.findAllByBodyPart("chest")).thenReturn(List.of(exercise, exercise2));
        Mockito.when(exerciseRepository.findAllByBodyPart("back")).thenReturn(List.of(exercise1));
        Mockito.when(trainingRepository.save(Mockito.any(Training.class)))
                .thenReturn(new Training(1L, "Generated training", List.of(exercise, exercise1, exercise2), null));

        TrainingDTO actual = trainingService.generateTraining(45, new String[]{"chest", "back"});

        assertEquals(actual, trainingDTO);
    }
}
