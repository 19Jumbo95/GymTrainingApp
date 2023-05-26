package pl.coderslab.gymtrainingapp.user;

import java.time.LocalDate;
import java.util.List;

public interface UserParamsService {
    List<UserParamsDTO> findAllByUser(Long userId);
    UserParamsDTO findByCreatedOn(String date);
    UserParamsDTO create(UserParamsDTO userParamsDTO);
}
