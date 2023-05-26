package pl.coderslab.gymtrainingapp.training;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.gymtrainingapp.exercise.Exercise;
import pl.coderslab.gymtrainingapp.exercise.ExerciseDTO;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/training")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;

    @GetMapping("/sample")
    public TrainingDTO getSample() {
        return new TrainingDTO(1L, "pull", List.of(new Exercise(1L, "lat pulldown", "back", 1, 1)), LocalDateTime.now());
    }

    @GetMapping("/findAll")
    public List<TrainingDTO> findAll() {return trainingService.findAll();}

    @GetMapping ("/findByName/{name}")
    TrainingDTO findByName(@PathVariable String name) {return trainingService.findByName(name);}

    @PostMapping("/create")
    public TrainingDTO create(@Valid @RequestBody TrainingDTO trainingDTO) {return trainingService.create(trainingDTO);}

    @PutMapping("/add-exercise/{exerciseName}/{trainingName}")
    public TrainingDTO addExercise(@PathVariable String exerciseName, @PathVariable String trainingName) {
        return trainingService.addExercise(exerciseName, trainingName);
    }
    @PostMapping("generate-training")
    public TrainingDTO generateTraining(@RequestParam Integer amountOfTime, @RequestParam String[] bodyParts) {
        return trainingService.generateTraining(amountOfTime, bodyParts);
    }
}
