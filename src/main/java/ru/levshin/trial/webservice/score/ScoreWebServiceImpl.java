package ru.levshin.trial.webservice.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levshin.trial.model.Score;
import ru.levshin.trial.service.ScoreService;

import javax.jws.WebService;

@WebService(
        serviceName = "ScoreWebService",
        portName = "ScorePort",
        targetNamespace = "http://service.trial/",
        endpointInterface = "ru.levshin.trial.webservice.score.ScoreWebService")
@Component
public class ScoreWebServiceImpl implements ScoreWebService {

    @Autowired
    private ScoreService scoreService;

    @Override
    public Score addScore(Score score) {
        return scoreService.addScore(score);
    }
}
