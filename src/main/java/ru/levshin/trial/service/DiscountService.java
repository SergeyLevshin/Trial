package ru.levshin.trial.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levshin.trial.dao.GenericDAO;
import ru.levshin.trial.model.Discount;
import ru.levshin.trial.model.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class DiscountService {

    private final GenericDAO<Discount> dao;

    private final ProductService productService;

    private final Random random = new Random();

    @Autowired
    public void setClazz() {
        dao.setClazz(Discount.class);
    }

    public Discount getByProduct(Product product) {
        return dao.getEntityManager()
                .createQuery("SELECT d FROM Discount d where d.product = :product", Discount.class)
                .setParameter("product", product)
                .getSingleResult();
    }

    public void generateRandomDiscount() {
        List<Product> products = productService.getAll();
        if (!products.isEmpty()) {
            Product product = products.get(random.nextInt(products.size()));
            Integer discount = random.nextInt(6) + 5;
            LocalDateTime now = LocalDateTime.now();
            dao.create(new Discount(product, discount, now, now.plusHours(1)));
        }
    }

    public Discount create(Discount discount) {
        return dao.create(discount);
    }

    public Discount getCurrentDiscount() {
        LocalDateTime now = LocalDateTime.now();
        return dao.getEntityManager()
                .createQuery("SELECT d FROm Discount d WHERE d.start <= :now AND d.end >= :now", Discount.class)
                .setParameter("now", now).getSingleResult();
    }
}
