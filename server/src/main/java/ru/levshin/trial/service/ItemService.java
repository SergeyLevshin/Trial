package ru.levshin.trial.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levshin.trial.dao.AbstractDAO;
import ru.levshin.trial.model.Item;
import ru.levshin.trial.model.Product;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private final AbstractDAO<Item> dao;

    @Autowired
    public void setClazz() {
        dao.setClazz(Item.class);
    }

    public List<Item> getByProduct(Product product) {
        return dao.getEntityManager()
                .createQuery("SELECT i FROM Item i where i.product = :product", dao.getClazz())
                .setParameter("product", product).getResultList();
    }

}
