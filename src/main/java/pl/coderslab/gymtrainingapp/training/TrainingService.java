package pl.coderslab.gymtrainingapp.training;

import java.util.List;

public interface TrainingService {
    List<TrainingDTO> findAll();
    TrainingDTO findByName(String name);
    TrainingDTO create(TrainingDTO trainingDTO);
    TrainingDTO addExercise(String exerciseName, String trainingName);
    TrainingDTO generateTraining(Integer amountOfTime, String[] bodyParts);
}
