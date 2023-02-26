package ru.levshin.trial.service;

import org.springframework.stereotype.Service;
import ru.levshin.trial.model.Customer;

@Service
public class UserService {

    public Customer getCurrentUser() {
        return new Customer();//todo
    }
}
