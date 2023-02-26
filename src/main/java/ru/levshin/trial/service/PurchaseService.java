package ru.levshin.trial.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levshin.trial.dao.AbstractDAO;
import ru.levshin.trial.exception.PaymentException;
import ru.levshin.trial.model.Customer;
import ru.levshin.trial.model.Item;
import ru.levshin.trial.model.Product;
import ru.levshin.trial.model.Purchase;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PurchaseService {

    private final AbstractDAO<Purchase> dao;

    private final ItemService itemService;

    private final ProductService productService;

    private final ReceiptService receiptService;

    private final CustomerService customerService;

    @Autowired
    public void setClazz() {
        dao.setClazz(Purchase.class);
    }

    public Long getTotalSum(Map<Long, Integer> purchases) {
        long sum = 0L;
        //todo if we may have a huge list of purchases this should be refactored to single interaction with database
        for (Map.Entry<Long, Integer> entry: purchases.entrySet()) {
            Product product = productService.getById(entry.getKey());
            sum += product.getPrice().scaleByPowerOfTen(2).longValue();
        }
        return sum;//todo скидки
    }

    @Transactional
    public void purchase(Map<Long, Integer> purchases, Long totalSum) throws PaymentException {
        Long actualSum = getTotalSum(purchases);
        if (!actualSum.equals(totalSum)) {
            throw new PaymentException("The total sum is different from actual sum of purchase");
        }
        Purchase purchase = new Purchase();
        purchase.setCustomer(customerService.getCurrent());
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setReceipt(receiptService.getReceiptNumber());
        purchase.setItems(new ArrayList<>());
        for (Map.Entry<Long, Integer> entry: purchases.entrySet()) {
            Product product = productService.getById(entry.getKey());
            Item item = new Item();
            item.setProduct(product);
            item.setAmount(entry.getValue());
            item.setFinalPrice(product.getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
            purchase.getItems().add(item);
        }
        dao.create(purchase);
    }

    public List<Purchase> getByCustomer(Long customerId) {
        return dao.getEntityManager()
                .createQuery("SELECT p FROM Purchase p where p.customer.id = :customerId", Purchase.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public List<Purchase> getByProduct(Long productId) {
        return dao.getEntityManager()
                .createQuery("select p from Purchase p " +
//                                        "join purchase_items pi2 on " +
//                                        "p.id = pi2.purchase_id \n" +
                                        "join Item i on i.product.id = :productId", Purchase.class)
                .setParameter("productId", productId).getResultList();
    }
}
