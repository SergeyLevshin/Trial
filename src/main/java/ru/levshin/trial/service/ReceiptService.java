package ru.levshin.trial.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levshin.trial.dao.AbstractDAO;
import ru.levshin.trial.model.Receipt;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReceiptService {

    private final AbstractDAO<Receipt> dao;

    @Autowired
    public void setClazz() {
        dao.setClazz(Receipt.class);
    }

    public String getReceiptNumber() {
        String number;
        Optional<Receipt> receiptOptional = dao.findAll().stream().findFirst();
        if (receiptOptional.isPresent()) {
            Receipt receipt = receiptOptional.get();
            if (receipt.getUpdateTime().getDayOfYear() == LocalDateTime.now().getDayOfYear()) {
                number = String.format("%05d", Integer.parseInt(receipt.getNumber()) + 1);
                receipt.setNumber(number);
                receipt.setUpdateTime(LocalDateTime.now());
                dao.update(receipt);
            } else {
                number = String.format("%05d", 100);
                dao.create(new Receipt(number, LocalDateTime.now()));
            }
        } else {
            number = String.format("%05d", 100);
            dao.create(new Receipt(number, LocalDateTime.now()));
        }
        return number;
    }
}
