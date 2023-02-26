package ru.levshin.trial.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Statistic {

    private int receiptsCount;

    private BigDecimal totalSum;

    private BigDecimal totalDiscountSum;

}
