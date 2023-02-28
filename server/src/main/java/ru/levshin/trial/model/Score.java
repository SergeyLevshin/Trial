package ru.levshin.trial.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "score")
@Data
public class Score {

    @Id
    @GeneratedValue
    private Long id;

    private Integer value;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Product product;
}
