package pl.coderslab.gymtrainingapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public UserDTO create(@Valid @RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }
    @PutMapping("/add-training")
    public UserDTO addTraining(@RequestParam String trainingName, @RequestParam Long userId) {
        return userService.addTraining(trainingName, userId);
    }
    @PutMapping("/generate-training")
    public UserDTO generateTraining(@RequestParam int amountOfDays, @RequestParam int amountOfTime, @RequestParam Long userId) {
        return userService.generateTrainingPlan(amountOfDays, amountOfTime, userId);
    }
}
