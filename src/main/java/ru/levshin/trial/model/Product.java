package ru.levshin.trial.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    @OneToMany
    private List<Score> scores;
}
