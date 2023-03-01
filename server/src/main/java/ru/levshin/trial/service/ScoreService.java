package ru.levshin.trial.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levshin.trial.dao.AbstractDAO;
import ru.levshin.trial.model.Customer;
import ru.levshin.trial.model.Score;

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

    public Integer getByCustomer(Customer customer) {
        return dao.getEntityManager()
                .createQuery("SELECT s FROm Score s where s.customer = :customer", dao.getClazz())
                .setParameter("customer", customer).getSingleResult().getValue();
    }
}
