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

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public Discount(Product product, Integer value, LocalDateTime startTime, LocalDateTime endTime) {
        this.product = product;
        this.value = value;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
