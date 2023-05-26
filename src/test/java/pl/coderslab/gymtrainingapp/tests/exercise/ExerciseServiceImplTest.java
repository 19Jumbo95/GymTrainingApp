package pl.coderslab.gymtrainingapp.tests.exercise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.coderslab.gymtrainingapp.exercise.*;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


@ExtendWith(MockitoExtension.class)
public class ExerciseServiceImplTest {

    @InjectMocks
    private ExerciseServiceImpl exerciseService;

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private ExerciseMapper exerciseMapper;

    @Test
    public void givenExercise_whenFindBySameName_thenFindExercise() {
        Exercise exercise = Exercise.builder().name("lat pulldown").bodyPart("back").build();
        Mockito.when(exerciseRepository.findByName("lat pulldown")).thenReturn(Optional.of(exercise));

        ExerciseDTO actual = exerciseService.findByName("lat pulldown");

        assertEquals("name", actual.getName());
    }

    @Test
    public void givenNewExercise_whenAdd_thenReturnedSavedExercise() {
        ExerciseDTO exerciseDTO = new ExerciseDTO(null, "lat pulldown", "back", 3, 3);
        Mockito.when(exerciseRepository.save(Mockito.any(Exercise.class)))
                .thenReturn(new Exercise(1L, "lat pulldown", "back", 3, 3));

        ExerciseDTO actual = exerciseService.create(exerciseDTO);

        assertEquals(new ExerciseDTO(1L, "lat pulldown", "back", 3, 3), actual);
    }
}
