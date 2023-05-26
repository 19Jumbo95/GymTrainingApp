package pl.coderslab.gymtrainingapp.exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    @Override
    public List<ExerciseDTO> findAllByBodyPart(String bodyPart) {
        return exerciseRepository.findAllByBodyPart(bodyPart)
                .stream()
                .map(exerciseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExerciseDTO> findAll() {
        return exerciseRepository.findAll()
                .stream()
                .map(exerciseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExerciseDTO findByName(String name) {
        return exerciseRepository.findByName(name)
                .map(exerciseMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Nie ma ćwiczenia o wskazanej nazwie"));
    }

    @Override
    public ExerciseDTO create(ExerciseDTO exerciseDTO) {
        Exercise exercise = exerciseRepository.save(exerciseMapper.fromDto(exerciseDTO));
        return exerciseMapper.toDto(exercise);
    }

    @Override
    public ExerciseDTO update(ExerciseDTO exerciseDTO) {
        return exerciseRepository.findByName(exerciseDTO.getName())
                .map((exercise -> {
                    exercise.setReps(exerciseDTO.getReps());
                    exercise.setSets(exerciseDTO.getSets());
                    return exercise;
                }))
                .map(exerciseRepository::save)
                .map(exerciseMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Nie ma ćwiczenia o wskazanej nazwie"));
    }

    @Override
    public String delete(Long id) {
        exerciseRepository.deleteById(id);
        return String.format("Ćwiczenia o id %s zostało usunięte", id);
    }
}
