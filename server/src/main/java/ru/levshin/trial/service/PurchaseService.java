package ru.levshin.trial.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levshin.trial.dao.AbstractDAO;
import ru.levshin.trial.exception.PaymentException;
import ru.levshin.trial.model.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PurchaseService {

    private final AbstractDAO<Purchase> dao;

    private final DiscountService discountService;

    private final ProductService productService;

    private final ReceiptService receiptService;

    private final CustomerService customerService;

    @Autowired
    public void setClazz() {
        dao.setClazz(Purchase.class);
    }

    public List<Purchase> getAll() {
        return dao.findAll();
    }

    public Long getTotalSum(Map<String, String> purchasesMap) {
        Map<Long, Integer> purchases = mapPurchases(purchasesMap);
        BigDecimal sum = BigDecimal.ZERO;
        Customer customer = customerService.getCurrent();
        Discount discount = discountService.getCurrentDiscount();
        for (Map.Entry<Long, Integer> entry: purchases.entrySet()) {
            Product product = productService.getById(entry.getKey());
            BigDecimal price = product.getPrice();
            int finalDiscount = 0;
            if (product.equals(discount.getProduct())) {
                if (purchases.size() >= 5 && customer.getDiscount2() != 0) {
                    finalDiscount = customer.getDiscount2();
                } else {
                    finalDiscount = customer.getDiscount1();
                }
            }
            if (finalDiscount > 18) {
                finalDiscount = 18;
            }
            price = price.multiply(BigDecimal.valueOf((100 - finalDiscount)/100d));
            sum = sum.add(price.multiply(BigDecimal.valueOf(entry.getValue())).scaleByPowerOfTen(2));
        }
        return sum.longValue();
    }

    @Transactional
    public void purchase(Map<String, String> purchasesMap, Long totalSum) throws PaymentException {
        Long actualSum = getTotalSum(purchasesMap);
        if (!actualSum.equals(totalSum)) {
            throw new PaymentException("The total sum is different from actual sum of purchase");
        }
        Map<Long, Integer> purchases = mapPurchases(purchasesMap);
        Purchase purchase = new Purchase();
        Customer customer = customerService.getCurrent();
        purchase.setCustomer(customer);
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setReceipt(receiptService.getReceiptNumber());
        purchase.setItems(new ArrayList<>());
        for (Map.Entry<Long, Integer> entry: purchases.entrySet()) {
            Product product = productService.getById(entry.getKey());
            Item item = new Item();
            item.setProduct(product);
            item.setAmount(entry.getValue());
            BigDecimal finalPrice = product.getPrice().multiply(BigDecimal.valueOf(entry.getValue()));
            Integer finalDiscount = 0;
            if (purchases.size() >= 5 && customer.getDiscount2() != 0) {
                finalPrice = finalPrice.multiply(BigDecimal.valueOf((100 - customer.getDiscount2())/100d));
                finalDiscount = customer.getDiscount2();
            } else {
                finalPrice = finalPrice.multiply(BigDecimal.valueOf((100 - customer.getDiscount1())/100d));
                finalDiscount = customer.getDiscount1();
            }
            item.setInitialPrice(product.getPrice());
            item.setFinalPrice(finalPrice);
            item.setFinalDiscount(finalDiscount);
            purchase.getItems().add(item);
        }
        dao.create(purchase);
    }

    @Transactional
    public String purchaseSoap(Map<String, String> purchases, Long totalSum) {
        try {
            purchase(purchases, totalSum);
        } catch (PaymentException ex) {
            return ex.getMessage();
        }
        return "Успешно";
    }

    public List<Purchase> getByCustomer(Long customerId) {
        return dao.getEntityManager()
                .createQuery("SELECT p FROM Purchase p where p.customer.id = :customerId", dao.getClazz())
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public List<Purchase> getByProduct(Long productId) {
        return dao.getEntityManager()
                .createQuery("select p from Purchase p " +
                                        "join Item i on i.product.id = :productId", dao.getClazz())
                .setParameter("productId", productId).getResultList();
    }

    private Map<Long, Integer> mapPurchases(Map<String, String> purchases) {
        return purchases.entrySet().stream()
                .collect(Collectors.toMap(e -> Long.parseLong(e.getKey()), e -> Integer.parseInt(e.getValue())));
    }
}
