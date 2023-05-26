package pl.coderslab.gymtrainingapp.user;

public interface UserService {
    UserDTO create(UserDTO userDTO);
    UserDTO addTraining(String trainingName, Long userId);
    UserDTO generateTrainingPlan(int amountOfDays, int amountOfTime, Long userId);
}
