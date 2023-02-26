package ru.levshin.trial.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.levshin.trial.dto.Statistic;
import ru.levshin.trial.model.Item;
import ru.levshin.trial.model.Purchase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class StatisticService {

    private static final String CUSTOMER = "Customer";

    private static final String PRODUCT = "Product";

    private final PurchaseService purchaseService;

    public Map<String, Statistic> getStatistic(Long customerId, Long productId) {
        Map<String, Statistic> statistic = new HashMap<>();
        List<Purchase> purchasesByCustomer = purchaseService.getByCustomer(customerId);
        long receiptSizeByCustomer = purchasesByCustomer
                .stream().map(Purchase::getItems)
                .flatMap(items -> items.stream().map(Item::getFinalPrice))
                .count();
        List<Purchase> purchasesByProduct = purchaseService.getByProduct(productId);
        long receiptSizeByProduct = purchasesByProduct
                .stream().map(Purchase::getItems)
                .flatMap(items -> items.stream().map(Item::getFinalPrice))
                .count();
    }
}
