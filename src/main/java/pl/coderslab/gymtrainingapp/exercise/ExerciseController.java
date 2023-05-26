package pl.coderslab.gymtrainingapp.exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/findAll")
    public List<ExerciseDTO> findAll() {return exerciseService.findAll();}
    @GetMapping("/findAllByBodyPart/{bodyPart}")
    public List<ExerciseDTO> findAllByBodyPart(@PathVariable String bodyPart) {return exerciseService.findAllByBodyPart(bodyPart);}
    @GetMapping("/findByName/{name}")
    public ExerciseDTO findByName(@PathVariable String name) {return exerciseService.findByName(name);}
    @PostMapping("/create")
    public ExerciseDTO create(@Valid @RequestBody ExerciseDTO exerciseDTO) {return exerciseService.create(exerciseDTO);}
    @PutMapping("/update")
    public ExerciseDTO update(@RequestBody ExerciseDTO exerciseDTO) {
        return exerciseService.update(exerciseDTO);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return exerciseService.delete(id);
    }
}
