package ru.levshin.trial.restcontroller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.levshin.trial.dto.Statistic;
import ru.levshin.trial.service.StatisticService;

import java.util.Map;

@RestController(value = "statistic")
@AllArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping
    public ResponseEntity<Map<String, Statistic>> getStatistic(
            @RequestParam("customerId") Long customerId, @RequestParam("productId") Long productId) {
        return ResponseEntity.ok(statisticService.getStatistic(customerId, productId));
    }
}
