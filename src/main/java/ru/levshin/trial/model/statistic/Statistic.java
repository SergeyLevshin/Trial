package ru.levshin.trial.model.statistic;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.levshin.trial.model.Customer;
import ru.levshin.trial.model.Product;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "statistic")
@NoArgsConstructor
public class Statistic {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Customer customer;

    @OneToOne
    private Product product;

    private int receiptsCount;

    private BigDecimal totalSum;

    private BigDecimal totalDiscount;

    public Statistic(int receiptsCount, Customer customer, Product product,
                     BigDecimal totalSum, BigDecimal totalDiscount) {
        this.receiptsCount = receiptsCount;
        this.customer = customer;
        this.product = product;
        this.totalSum = totalSum;
        this.totalDiscount = totalDiscount;
    }
}
