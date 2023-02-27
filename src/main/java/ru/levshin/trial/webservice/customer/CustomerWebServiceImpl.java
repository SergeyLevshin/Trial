package ru.levshin.trial.webservice.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levshin.trial.model.Customer;
import ru.levshin.trial.service.CustomerService;

import javax.jws.WebService;
import java.util.List;

@WebService(
        serviceName = "CustomerService",
        portName = "CustomerPort",
        targetNamespace = "http://service.trial/",
        endpointInterface = "ru.levshin.trial.webservice.customer.CustomerWebService")
@Component
public class CustomerWebServiceImpl implements CustomerWebService {

    @Autowired
    private CustomerService customerService;

    @Override
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @Override
    public Customer updateDiscounts(Long id, Integer discount1, Integer discount2) {
        return customerService.updateDiscounts(id, discount1, discount2);
    }
}
