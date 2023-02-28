package ru.levshin.trial.webservice.purchase;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.Map;

@WebService(targetNamespace = "http://service.trial/", name = "PurchaseWebService")
public interface PurchaseWebService {

    @WebResult(name = "totalSum", targetNamespace = "")
    @RequestWrapper(
            localName = "gettotalSum",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.purchase.PurchaseWebService")
    @WebMethod(action = "urn:TotalSum")
    @ResponseWrapper(
            localName = "totalSumResponce",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.purchase.PurchaseWebServiceImpl")
    Long getTotalSum(@WebParam(name = "purchases") Map<Long, Integer> purchases);

    @WebResult(name = "purchase", targetNamespace = "")
    @RequestWrapper(
            localName = "purchase",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.purchase.PurchaseWebService")
    @WebMethod(action = "urn:Purchase")
    @ResponseWrapper(
            localName = "purchaseResponce",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.purchase.PurchaseWebServiceImpl")
    String purchase(@WebParam(name = "purchases") Map<Long, Integer> purchases,
                    @WebParam(name = "totalSum") Long totalSum);
}
