package ru.levshin.trial.restcontroller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.levshin.trial.model.Score;
import ru.levshin.trial.service.ScoreService;

@RestController
@RequestMapping("/score")
@AllArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @PostMapping
    public ResponseEntity<Score> addScore(@RequestBody Score score) {
        return new ResponseEntity<>(scoreService.addScore(score), HttpStatus.CREATED);
    }
}
