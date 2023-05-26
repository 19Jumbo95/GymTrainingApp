package pl.coderslab.gymtrainingapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user-params")
@RequiredArgsConstructor
public class UserParamsController {

    private final UserParamsService userParamsService;

    @PostMapping("/create")
    public UserParamsDTO create(@RequestBody UserParamsDTO userParamsDTO) {
        return userParamsService.create(userParamsDTO);
    }

    @GetMapping("/findAllById/{userId}")
    public List<UserParamsDTO> findAllById(@PathVariable Long userId) {return userParamsService.findAllByUser(userId);}

    @GetMapping("/findByDate/{date}")
    public UserParamsDTO findByDate(@PathVariable String date) {return userParamsService.findByCreatedOn(date);}
}
