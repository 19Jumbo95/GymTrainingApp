package pl.coderslab.gymtrainingapp.exercise;

import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {
    public ExerciseDTO toDto(Exercise exercise) {
        return ExerciseDTO.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .bodyPart(exercise.getBodyPart())
                .reps(exercise.getReps())
                .sets(exercise.getSets())
                .build();
    }
    public Exercise fromDto(ExerciseDTO exerciseDTO) {
        return new Exercise(exerciseDTO.getId(), exerciseDTO.getName(), exerciseDTO.getBodyPart(), exerciseDTO.getReps(), exerciseDTO.getSets());
    }
}
