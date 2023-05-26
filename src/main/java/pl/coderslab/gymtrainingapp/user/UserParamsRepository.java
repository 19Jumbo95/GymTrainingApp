package pl.coderslab.gymtrainingapp.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserParamsRepository extends JpaRepository<UserParams, Long> {
    List<UserParams> findAllByUser(User user);
    Optional<UserParams> findByCreated0n(LocalDate localDate);
}
