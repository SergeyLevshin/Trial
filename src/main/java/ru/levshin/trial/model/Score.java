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

    @OneToOne
    private Customer customer;

    @OneToOne
    private Product product;
}
