package ru.levshin.trial.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.levshin.trial.model.Customer;
import ru.levshin.trial.service.CustomerService;

import java.util.List;

@RestController(value = "customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.create(customer), HttpStatus.CREATED);
    }

    @PatchMapping()
    public ResponseEntity<Customer> updateDiscounts(
            @RequestParam("id") Long id,
            @RequestParam("discount1") Integer discount1,
            @RequestParam("discount2") Integer discount2) {
        return new ResponseEntity<>(customerService.updateDiscounts(id, discount1, discount2), HttpStatus.NO_CONTENT);
    }
}
