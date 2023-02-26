package ru.levshin.trial.service;

import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    public String getReceiptNumber() {
        return new RandomString(5).nextString();//todo
    }
}
