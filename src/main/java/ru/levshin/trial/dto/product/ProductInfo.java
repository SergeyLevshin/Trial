package ru.levshin.trial.dto.product;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ProductInfo {

    private String description;

    private BigDecimal averageScore;

    private Map<Integer, Long> scoreDistribution;

    private Integer currentScore;
}
