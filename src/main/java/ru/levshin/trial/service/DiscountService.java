package ru.levshin.trial.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levshin.trial.dao.GenericDAO;
import ru.levshin.trial.model.Discount;
import ru.levshin.trial.model.Product;

@Service
@AllArgsConstructor
public class DiscountService {

    private final GenericDAO<Discount> dao;

    @Autowired
    public void setClazz() {
        dao.setClazz(Discount.class);
    }

    public Discount getByProduct(Product product) {
        return dao.getEntityManager()
                .createQuery("SELECT d FROM Discount d where d.product = :product", Discount.class)
                .getSingleResult();
    }
}
