package ru.levshin.trial.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer discount1;

    @Column
    private Integer discount2;
}
