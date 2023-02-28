package ru.levshin.trial.scheduled;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.levshin.trial.service.DiscountService;
import ru.levshin.trial.service.StatisticService;

import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class ScheduledTasks {

    private final StatisticService statisticService;

    private final DiscountService discountService;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    public void generateStatistic() {
        statisticService.generateStatistic();
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    public void generateDiscount() {
        discountService.generateRandomDiscount();
    }
}
