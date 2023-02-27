package ru.levshin.trial.restcontroller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.levshin.trial.exception.PaymentException;
import ru.levshin.trial.service.PurchaseService;

import java.util.Map;

@RestController
@RequestMapping("/purchase")
@AllArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("/totalSum")
    public ResponseEntity<Long> getTotalSum(@RequestParam Map<Long, Integer> purchases) {
        return ResponseEntity.ok(purchaseService.getTotalSum(purchases));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> purchase(@RequestBody Map<Long, Integer> purchases, Long totalSum)
            throws PaymentException {
        purchaseService.purchase(purchases, totalSum);
        return ResponseEntity.noContent().build();
    }
}
