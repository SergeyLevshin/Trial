package ru.levshin.trial.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "discount")
@Data
@NoArgsConstructor
public class Discount {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Product product;

    private Integer value;

    private LocalDateTime start;

    private LocalDateTime end;

    public Discount(Product product, Integer value, LocalDateTime start, LocalDateTime end) {
        this.product = product;
        this.value = value;
        this.start = start;
        this.end = end;
    }
}
