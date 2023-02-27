package ru.levshin.trial.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue
    private Long id;

    private String number;

    private LocalDateTime updateTime;

    public Receipt(String number, LocalDateTime updateTime) {
        this.number = number;
        this.updateTime = updateTime;
    }
}
