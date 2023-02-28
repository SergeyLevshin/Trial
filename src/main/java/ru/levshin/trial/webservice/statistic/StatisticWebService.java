package ru.levshin.trial.webservice.statistic;

import ru.levshin.trial.model.statistic.Statistic;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.Map;

@WebService(targetNamespace = "http://service.trial/", name = "StatisticWebService")
public interface StatisticWebService {

    @WebResult(name = "statistic", targetNamespace = "")
    @RequestWrapper(
            localName = "statistic",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.statistic.StatisticWebService")
    @WebMethod(action = "urn:Statistic")
    @ResponseWrapper(
            localName = "statisticResponce",
            targetNamespace = "http://service.trial/",
            className = "ru.levshin.trial.webservice.statistic.StatisticWebServiceImpl")
    Map<String, Statistic> getStatistic(@WebParam(name = "customerId") Long customerId,
                                        @WebParam(name = "productId") Long productId);
}
