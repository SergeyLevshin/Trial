package ru.levshin.trial.restcontroller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.levshin.trial.model.statistic.Statistic;
import ru.levshin.trial.service.StatisticService;

import java.util.Map;

@RestController
@RequestMapping("/statistic")
@AllArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping
    public ResponseEntity<Map<String, Statistic>> getStatistic(
            @RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam(value = "productId", required = false) Long productId) {
        return ResponseEntity.ok(statisticService.getStatistic(customerId, productId));
    }
}
