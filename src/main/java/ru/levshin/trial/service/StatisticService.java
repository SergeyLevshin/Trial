package ru.levshin.trial.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levshin.trial.dao.AbstractDAO;
import ru.levshin.trial.model.Customer;
import ru.levshin.trial.model.Item;
import ru.levshin.trial.model.Product;
import ru.levshin.trial.model.Purchase;
import ru.levshin.trial.model.statistic.Statistic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatisticService {

    private static final String CUSTOMER = "Customer";

    private static final String PRODUCT = "Product";

    private final AbstractDAO<Statistic> dao;

    @Autowired
    public void setClazz() {
        dao.setClazz(Statistic.class);
    }

    private final ItemService itemService;

    private final PurchaseService purchaseService;

    public Long create(Statistic statistic) {
        return dao.create(statistic).getId();
    }

    public void generateStatistic() {
        List<Purchase> purchases = purchaseService.getAll();
        purchases.stream()
                .collect(Collectors.groupingBy(Purchase::getCustomer))
                .forEach((customer, purchaseList) -> create(generateForCustomer(customer, purchases)));

        List<Product> products = purchases.stream()
                .flatMap(purchase -> purchase.getItems().stream().map(Item::getProduct))
                .distinct().collect(Collectors.toList());
        products.forEach(product -> create(generateForProduct(product)));
    }


    public Map<String, Statistic> getStatistic(Long customerId, Long productId) {
        Map<String, Statistic> statMap = new HashMap<>();
        statMap.put(CUSTOMER, getByCustomer(customerId));
        statMap.put(PRODUCT, getByProduct(productId));
        return statMap;
    }

    private Statistic generateForCustomer(Customer customer, List<Purchase> purchases) {
        BigDecimal sumByCustomer = purchases
                .stream().map(Purchase::getItems)
                .flatMap(items -> items.stream().map(Item::getFinalPrice))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal discountSumByCustomer = purchases
                .stream().map(Purchase::getItems)
                .flatMap(items -> items.stream().map(item -> item.getInitialPrice().subtract(item.getFinalPrice())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Statistic(purchases.size(), customer, null, sumByCustomer, discountSumByCustomer);
    }

    private Statistic generateForProduct(Product product) {
        List<Item> items = itemService.getByProduct(product);
        BigDecimal sumByProduct = items.stream().map(Item::getFinalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal discountSumByProduct = items.stream()
                .map(item -> item.getInitialPrice().subtract(item.getFinalPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Statistic(items.size(), null, product, sumByProduct, discountSumByProduct);
    }

    private Statistic getByProduct(Long productId) {
        return dao.getEntityManager()
                .createQuery("SELECT s FROm Statistic s WHERE s.product.id = :productId", Statistic.class)
                .setParameter("productId", productId).getSingleResult();
    }
    private Statistic getByCustomer(Long customerId) {
        return dao.getEntityManager()
                .createQuery("SELECT s FROm Statistic s WHERE s.customer.id = :customerId", Statistic.class)
                .setParameter("customerId", customerId).getSingleResult();
    }
}