package ru.levshin.trial.webservice.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levshin.trial.model.statistic.Statistic;
import ru.levshin.trial.service.StatisticService;

import javax.jws.WebService;
import java.util.Map;

@WebService(
        serviceName = "ScoreWebService",
        portName = "ScorePort",
        targetNamespace = "http://service.trial/",
        endpointInterface = "ru.levshin.trial.webservice.statistic.StatisticWebService")
@Component
public class StatisticWebServiceImpl implements StatisticWebService {

    @Autowired
    private StatisticService statisticService;

    @Override
    public Map<String, Statistic> getStatistic(Long customerId, Long productId) {
        return statisticService.getStatistic(customerId, productId);
    }
}
