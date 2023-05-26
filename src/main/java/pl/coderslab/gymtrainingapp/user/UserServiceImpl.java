package pl.coderslab.gymtrainingapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.gymtrainingapp.training.Training;
import pl.coderslab.gymtrainingapp.training.TrainingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    @Override
    public UserDTO create(UserDTO userDTO) {
        User user = userRepository.save(fromDto(userDTO));
        return toDto(user);
    }

    @Override
    public UserDTO addTraining(String trainingName, Long userId) {
        Training training = trainingRepository.findByName(trainingName)
                .orElseThrow(() -> new RuntimeException("Nie ma treningu o wskazanej nazwie"));
        UserDTO userDTO = userRepository.findById(userId)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Nie ma użytkownika o wskazanym ID"));
        List<Training> trainingList = userDTO.getTrainings();
        trainingList.add(training);
        userDTO.setTrainings(trainingList);
        userRepository.save(fromDto(userDTO));
        return userDTO;
    }

    @Override
    public UserDTO generateTrainingPlan(int amountOfDays, int amountOfTime, Long userId) {
        List<Training> trainingList = new ArrayList<>();
        if (amountOfDays == 1 && amountOfTime == 1) {
            trainingList.add(trainingRepository.findByName("FBW 1H").orElse(null));
        }
        if (amountOfDays == 1 && amountOfTime == 2) {
            trainingList.add(trainingRepository.findByName("FBW 2H").orElse(null));
        }
        if (amountOfDays == 2 && amountOfTime == 2) {
            trainingList.add(trainingRepository.findByName("FBW A").orElse(null));
            trainingList.add(trainingRepository.findByName("FBW B").orElse(null));
        }
        if (amountOfDays == 3 && amountOfTime == 2) {
            trainingList.add(trainingRepository.findByName("FBW A").orElse(null));
            trainingList.add(trainingRepository.findByName("FBW B").orElse(null));
            trainingList.add(trainingRepository.findByName("FBW C").orElse(null));
        }
        if (amountOfDays == 4 && amountOfTime == 2) {
            trainingList.add(trainingRepository.findByName("PUSH").orElse(null));
            trainingList.add(trainingRepository.findByName("PULL").orElse(null));
            trainingList.add(trainingRepository.findByName("UPPER").orElse(null));
            trainingList.add(trainingRepository.findByName("LOWER").orElse(null));
        }
        return userRepository.findById(userId)
                .map(user -> {
                    user.setTrainings(trainingList);
                    return user;
                })
                .map(userRepository::save)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Użytkownik o wskazanym id nie istnieje"));
    }

    public UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .trainings(user.getTrainings())
                .build();
    }
    public User fromDto(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getTrainings());
    }
}
