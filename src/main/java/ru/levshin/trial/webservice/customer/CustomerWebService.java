package ru.levshin.trial.webservice.customer;

import ru.levshin.trial.model.Customer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

@WebService(targetNamespace = "http://service.trial/", name = "CustomerWebService")
public interface CustomerWebService {

    @WebResult(name = "customers", targetNamespace = "")
    @RequestWrapper(
            localName = "getAll",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.customer.CustomerWebService")
    @WebMethod(action = "urn:GetAll")
    @ResponseWrapper(
            localName = "getAllCustomerResponce",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.customer.CustomerWebServiceImpl")
    List<Customer> getAll();

    @WebResult(name = "customers", targetNamespace = "")
    @RequestWrapper(
            localName = "updateDiscounts",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.customer.CustomerWebService")
    @WebMethod(action = "urn:UpdateDiscounts")
    @ResponseWrapper(
            localName = "updateDiscountsResponce",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.customer.CustomerWebServiceImpl")
    Customer updateDiscounts(@WebParam(name = "id") Long id,
                             @WebParam(name = "discount1") Integer discount1,
                             @WebParam(name = "discount2") Integer discount2);
}
