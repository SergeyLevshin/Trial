package ru.levshin.trial.webservice.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levshin.trial.dto.product.ProductInfo;
import ru.levshin.trial.model.Product;
import ru.levshin.trial.service.ProductService;

import javax.jws.WebService;
import java.util.List;

@WebService(
        serviceName = "ProductWebService",
        portName = "ProductPort",
        targetNamespace = "http://service.trial/",
        endpointInterface = "ru.levshin.trial.webservice.product.ProductWebService")
@Component
public class ProductWebServiceImpl implements ProductWebService {

    @Autowired
    private ProductService productService;


    @Override
    public ProductInfo getInfo(Long id) {
        return productService.getInfo(id);
    }

    @Override
    public List<Product> getAll() {
        return productService.getAll();
    }
}
