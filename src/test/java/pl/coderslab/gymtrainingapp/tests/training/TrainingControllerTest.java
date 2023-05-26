package pl.coderslab.gymtrainingapp.tests.training;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.coderslab.gymtrainingapp.exercise.Exercise;
import pl.coderslab.gymtrainingapp.training.TrainingController;
import pl.coderslab.gymtrainingapp.training.TrainingDTO;
import pl.coderslab.gymtrainingapp.training.TrainingService;

import java.awt.*;
import java.util.List;

@WebMvcTest(TrainingController.class)
public class TrainingControllerTest {

    @MockBean
    private TrainingService trainingService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenNewTraining_whenGenerate_thenSaveThatTraining() throws Exception {
        Exercise exercise = new Exercise(1L, "Bench press", "chest", 6, 3);
        Exercise exercise1 = new Exercise(2L, "Lat pulldown", "back", 6, 3);
        Exercise exercise2 = new Exercise(3L, "Dumbbell press", "chest", 6, 3);
        Mockito.when(trainingService.generateTraining(45, new String[]{"chest", "back"})).thenReturn(new TrainingDTO(1L, "Generated training", List.of(exercise, exercise1, exercise2), null));

        mockMvc.perform(MockMvcRequestBuilders.post("/training/generate-training")
                        .param("amountOfTime", "45")
                        .param("bodyParts", "chest")
                        .param("bodyParts", "back")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(new TrainingDTO(1L, "Generated training", List.of(exercise, exercise1, exercise2), null))));
    }
}

