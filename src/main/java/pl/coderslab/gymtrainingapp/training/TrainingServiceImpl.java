package pl.coderslab.gymtrainingapp.training;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.gymtrainingapp.exercise.Exercise;
import pl.coderslab.gymtrainingapp.exercise.ExerciseDTO;
import pl.coderslab.gymtrainingapp.exercise.ExerciseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public List<TrainingDTO> findAll() {
        return trainingRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TrainingDTO findByName(String name) {
        return trainingRepository.findByName(name)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Nie ma treningu o wskazanej nazwie"));
    }

    @Override
    public TrainingDTO create(TrainingDTO trainingDTO) {
        Training training = trainingRepository.save(fromDto(trainingDTO));
        return toDto(training);
    }

    @Override
    public TrainingDTO addExercise(String exerciseName, String trainingName) {
        Exercise exercise = exerciseRepository.findByName(exerciseName)
                .orElseThrow(() -> new RuntimeException("Nie ma ćwiczenia o wskazanej nazwie"));
        TrainingDTO trainingDTO = trainingRepository.findByName(trainingName)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Nie ma treningu o wskazanej nazwie"));
        List<Exercise> exerciseList = trainingDTO.getExerciseList();
        exerciseList.add(exercise);
        trainingDTO.setExerciseList(exerciseList);
        trainingRepository.save(fromDto(trainingDTO));
        return trainingDTO;
    }

    public TrainingDTO generateTraining(Integer amountOfTime, String[] bodyParts) {
        Random random = new Random();
        int randomNumberChecker = -1;
        List<Exercise> exerciseList = new ArrayList<>();
        int amountOfExercises = amountOfTime / 15;
        int amountOfExercisesOnOneBodyPart = amountOfExercises / bodyParts.length;
        int modulo = amountOfExercises % bodyParts.length;
        for (String bodyPart : bodyParts) {
            for (int i = 0; i < amountOfExercisesOnOneBodyPart; i++) {
                List<Exercise> exerciseRepositoryAllByBodyPart = exerciseRepository.findAllByBodyPart(bodyPart);
                int randomNumber = random.nextInt(exerciseRepositoryAllByBodyPart.size());
                while (randomNumber == randomNumberChecker) {
                    randomNumber = random.nextInt(exerciseRepositoryAllByBodyPart.size());
                }
                exerciseList.add(exerciseRepositoryAllByBodyPart.get(randomNumber));
                randomNumberChecker = randomNumber;

            }
        }
        for (int i = 0; i < modulo; i++) {
            List<Exercise> exerciseRepositoryAllByBodyPart = exerciseRepository.findAllByBodyPart(bodyParts[i]);
            int randomNumber = random.nextInt(exerciseRepositoryAllByBodyPart.size());
            while (exerciseList.contains(exerciseRepositoryAllByBodyPart.get(randomNumber))) {
                randomNumber = random.nextInt(exerciseRepositoryAllByBodyPart.size());
            }
            exerciseList.add(exerciseRepositoryAllByBodyPart.get(randomNumber));
        }
        TrainingDTO trainingDTO = TrainingDTO.builder()
                .name("Generated training")
                .exerciseList(exerciseList)
                .build();
        Training training = trainingRepository.save(fromDto(trainingDTO));
        return toDto(training);
    }

    public TrainingDTO toDto(Training training) {
        return TrainingDTO.builder()
                .id(training.getId())
                .name(training.getName())
                .exerciseList(training.getExerciseList())
                .executionDate(training.getExecutionDate())
                .build();
    }
    public Training fromDto(TrainingDTO trainingDTO) {
        return new Training(trainingDTO.getId(), trainingDTO.getName(), trainingDTO.getExerciseList(), trainingDTO.getExecutionDate());
    }
}
