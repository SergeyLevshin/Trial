package ru.levshin.trial.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.levshin.trial.dto.product.ProductDTO;
import ru.levshin.trial.dto.product.ProductInfo;
import ru.levshin.trial.model.Product;
import ru.levshin.trial.service.ProductService;

import java.util.List;

@RestController(value = "product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/info")
    public ResponseEntity<ProductInfo> getInfo(@RequestParam("id") Long id) {
        return ResponseEntity.ok(productService.getInfo(id));
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody Product product) {
        return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
    }
}
