package ru.levshin.trial.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Product product;

    private Integer value;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;
}
