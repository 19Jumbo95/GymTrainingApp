package pl.coderslab.gymtrainingapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserParamsServiceImpl implements UserParamsService{

    private final UserRepository userRepository;
    private final UserParamsRepository userParamsRepository;

    @Override
    public List<UserParamsDTO> findAllByUser(Long userId) {

        return userParamsRepository.findAllByUser(userRepository.getReferenceById(userId))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserParamsDTO findByCreatedOn(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return userParamsRepository.findByCreated0n(localDate)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Nie ma parametrów dla podanej daty"));
    }

    @Override
    public UserParamsDTO create(UserParamsDTO userParamsDTO) {
        UserParams userParams = userParamsRepository.save(fromDto(userParamsDTO));
        return toDto(userParams);
    }

    public UserParamsDTO toDto(UserParams userParams) {
        return UserParamsDTO.builder()
                .id(userParams.getId())
                .userId(userParams.getUser().getId())
                .weight(userParams.getWeight())
                .createdOn(userParams.getCreated0n())
                .build();
    }
    public UserParams fromDto(UserParamsDTO userParamsDTO) {
        return new UserParams(userParamsDTO.getId(), userRepository.getReferenceById(userParamsDTO.getUserId()), userParamsDTO.getWeight(), userParamsDTO.getCreatedOn());
    }
}
