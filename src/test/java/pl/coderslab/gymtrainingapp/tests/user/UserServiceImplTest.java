package pl.coderslab.gymtrainingapp.tests.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.coderslab.gymtrainingapp.training.Training;
import pl.coderslab.gymtrainingapp.training.TrainingRepository;
import pl.coderslab.gymtrainingapp.user.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceImplTest {
    private UserRepository userRepository;
    private TrainingRepository trainingRepository;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        trainingRepository = Mockito.mock(TrainingRepository.class);
        userService = new UserServiceImpl(trainingRepository, userRepository);
    }

    @Test
    public void givenTrainingParam_whenGenerateTrainingPlan_thenShouldReturnUserWithTrainingPlan() {
        Training training = new Training(1L, "FBW 1H", null, null);
        UserDTO userDTO = new UserDTO(1L, "Maciek", List.of(training));
        User user = new User(1L, "Maciek", null);
        Mockito.when(trainingRepository.findByName("FBW 1H")).thenReturn(Optional.of(training));
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(new User(1L, "Maciek", List.of(training)));

        UserDTO actual = userService.generateTrainingPlan(1, 1, 1L);

        assertEquals(actual, userDTO);
    }
}
