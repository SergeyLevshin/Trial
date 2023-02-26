package ru.levshin.trial.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "purchase")
@Data
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    private LocalDateTime purchaseDate;

    private String receipt;

    @ManyToMany
    private List<Item> items;
}
