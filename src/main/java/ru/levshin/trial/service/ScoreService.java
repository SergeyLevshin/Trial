package ru.levshin.trial.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levshin.trial.dao.AbstractDAO;
import ru.levshin.trial.model.Product;
import ru.levshin.trial.model.Score;

import java.util.List;

@Service
@AllArgsConstructor
public class ScoreService {

    private final AbstractDAO<Score> dao;

    @Autowired
    public void setClazz() {
        dao.setClazz(Score.class);
    }

    public Score addScore(Score score) {
        return dao.create(score);
    }
}
