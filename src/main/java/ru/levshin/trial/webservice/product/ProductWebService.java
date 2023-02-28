package ru.levshin.trial.webservice.product;

import ru.levshin.trial.dto.product.ProductInfo;
import ru.levshin.trial.model.Product;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

@WebService(targetNamespace = "http://service.trial/", name = "ProductWebService")
public interface ProductWebService {

    @WebResult(name = "product", targetNamespace = "")
    @RequestWrapper(
            localName = "getInfo",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.product.ProductWebService")
    @WebMethod(action = "urn:GetInfo")
    @ResponseWrapper(
            localName = "getInfoResponce",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.product.ProductWebServiceImpl")
    ProductInfo getInfo(@WebParam(name = "id") Long id);

    @WebResult(name = "products", targetNamespace = "")
    @RequestWrapper(
            localName = "getAll",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.customer.ProductWebService")
    @WebMethod(action = "urn:GetAll")
    @ResponseWrapper(
            localName = "getAllProductResponce",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.customer.ProductWebServiceImpl")
    List<Product> getAll();
}
