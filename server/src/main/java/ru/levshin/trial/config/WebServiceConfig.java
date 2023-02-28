package ru.levshin.trial.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.levshin.trial.webservice.purchase.PurchaseWebServiceImpl;
import ru.levshin.trial.webservice.customer.CustomerWebServiceImpl;
import ru.levshin.trial.webservice.product.ProductWebServiceImpl;
import ru.levshin.trial.webservice.score.ScoreWebServiceImpl;
import ru.levshin.trial.webservice.statistic.StatisticWebServiceImpl;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint customerEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new CustomerWebServiceImpl());
        endpoint.publish("/Customers");
        return endpoint;
    }

    @Bean
    public Endpoint productEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new ProductWebServiceImpl());
        endpoint.publish("/Products");
        return endpoint;
    }

    @Bean
    public Endpoint purchaseEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new PurchaseWebServiceImpl());
        endpoint.publish("/Purchases");
        return endpoint;
    }

    @Bean
    public Endpoint scoreEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new ScoreWebServiceImpl());
        endpoint.publish("/Scores");
        return endpoint;
    }
    @Bean
    public Endpoint statisticEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new StatisticWebServiceImpl());
        endpoint.publish("/Statistic");
        return endpoint;
    }
}
