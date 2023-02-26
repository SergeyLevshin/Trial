package ru.levshin.trial.dto.product;

import ru.levshin.trial.model.Product;

import java.math.BigDecimal;

public class ProductDTO {

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    private Long id;

    private String name;

    private BigDecimal price;
}
