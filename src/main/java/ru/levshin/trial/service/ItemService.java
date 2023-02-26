package ru.levshin.trial.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levshin.trial.dao.AbstractDAO;
import ru.levshin.trial.model.Item;

@Service
@AllArgsConstructor
public class ItemService {

    private final AbstractDAO<Item> dao;

    @Autowired
    public void setClazz() {
        dao.setClazz(Item.class);
    }
}
