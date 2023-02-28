package ru.levshin.trial.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
@Data
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Product product;

    private Integer amount;

    private BigDecimal initialPrice;

    private BigDecimal finalPrice;

    private Integer finalDiscount;
}
