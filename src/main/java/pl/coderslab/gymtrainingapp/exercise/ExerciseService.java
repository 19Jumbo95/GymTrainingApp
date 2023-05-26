package pl.coderslab.gymtrainingapp.exercise;

import java.util.List;

public interface ExerciseService {

    List<ExerciseDTO> findAllByBodyPart(String bodyPart);
    List<ExerciseDTO> findAll();
    ExerciseDTO findByName(String name);
    ExerciseDTO create(ExerciseDTO exerciseDTO);
    ExerciseDTO update(ExerciseDTO exerciseDTO);
    String delete(Long id);
}
