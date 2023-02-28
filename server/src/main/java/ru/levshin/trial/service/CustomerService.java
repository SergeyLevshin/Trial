package ru.levshin.trial.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levshin.trial.dao.AbstractDAO;
import ru.levshin.trial.model.Customer;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final AbstractDAO<Customer> dao;

    @Autowired
    public void setClazz() {
        dao.setClazz(Customer.class);
    }

    public Long create(Customer customer) {
        return dao.create(customer).getId();
    }

    public Customer updateDiscounts(Long id, Integer discount1, Integer discount2) {
        Customer customer = dao.findById(id);
        customer.setDiscount1(discount1);
        customer.setDiscount2(discount2);
        return dao.update(customer);
    }

    public List<Customer> getAll() {
        return dao.findAll();
    }

    public Customer getById(Long id) {
        return dao.findById(id);
    }

    public Customer getCurrent() {
        return getAll().stream().findFirst().orElse(new Customer());
    }
}
