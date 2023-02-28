package ru.levshin.trial.webservice.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levshin.trial.service.PurchaseService;

import javax.jws.WebService;
import java.util.Map;

@WebService(
        serviceName = "PurchaseWebService",
        portName = "PurchasePort",
        targetNamespace = "http://service.trial/",
        endpointInterface = "ru.levshin.trial.webservice.purchase.PurchaseWebService")
@Component
public class PurchaseWebServiceImpl implements PurchaseWebService {

    @Autowired
    private PurchaseService purchaseService;

    @Override
    public Long getTotalSum(Map<String, String> purchases) {
        return purchaseService.getTotalSum(purchases);
    }

    @Override
    public String purchase(Map<String, String> purchases, Long totalSum) {
        return purchaseService.purchaseSoap(purchases, totalSum);
    }
}
